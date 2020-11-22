package eiffle.PandaMeiyaReykaSuki.demo;

import java.time.LocalDateTime;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import eiffle.PandaMeiyaReykaSuki.db.ChoiceDAO;
import eiffle.PandaMeiyaReykaSuki.http.CreateChoiceRequest;
import eiffle.PandaMeiyaReykaSuki.http.CreateChoiceResponse;
import eiffle.PandaMeiyaReykaSuki.model.Choice;

public class CreateChoiceHandler implements RequestHandler<CreateChoiceRequest,CreateChoiceResponse> {

	LambdaLogger logger;
	
    private AmazonS3 s3 = null;
    
    public CreateChoiceHandler() {}

  
    
    boolean createChoice(int limitMember, int numAlt, String description, String localDateTime) throws Exception{
    	if (logger != null) { logger.log("in createChoice"); }
    	ChoiceDAO dao = new ChoiceDAO();
    	
    	String choiceID = UUID.randomUUID().toString();
//    	boolean exist = dao.getChoice(choiceID);
    	
//    	if(exist) {return false;}
    	
    	if(numAlt < 2 && numAlt > 5) {
    		Choice choice = new Choice(choiceID, limitMember, numAlt, description,localDateTime);
    		return dao.addChoice(choice);
    	} else {
    		return false;
    	}	
    }
    	
    

	@Override
	public CreateChoiceResponse handleRequest(CreateChoiceRequest req, Context context) {
		logger = context.getLogger();
		logger.log(req.toString());
		
		CreateChoiceResponse response;
		
		try {
			if(createChoice(req.limitMember, req.numAlt, req.description, req.localDateTime)) {
				response = new CreateChoiceResponse(req.choiceID);
			} else {
				response = new CreateChoiceResponse(req.choiceID, 422);
			}
		} catch (Exception e) {
			response = new CreateChoiceResponse("Unable to create choice: " + req.choiceID + "(" + e.getMessage() + ")", 400);
		}

		return response;
	}
}