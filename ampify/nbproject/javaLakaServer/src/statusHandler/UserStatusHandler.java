package statusHandler;

import constants.StatusCode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import request.GetStatusRequest;
import tools.DBConnection;

public class UserStatusHandler {
    private final GetStatusRequest getStatusRequest;
    
    public UserStatusHandler(GetStatusRequest getStatusRequest){
        this.getStatusRequest = getStatusRequest;
    }
    
    public StatusResponse getResponse(){
        String query = "SELECT * FROM onlineusers WHERE username=?";
        try{
            PreparedStatement pdt = DBConnection.connectDB().prepareStatement(query);
            pdt.setString(1,this.getStatusRequest.getUserName());
            ResultSet rs = pdt.executeQuery();
            if(rs.next()){
                return new StatusResponse(null, StatusCode.ONLINE);
            }
        } catch(SQLException e) {}
        return new StatusResponse(null, StatusCode.OFFLINE);
    }
}
