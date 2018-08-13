package usermanager;


public class LogMessage {

    private int HttpStatus;
    private String HttpMethod;
    private String path;
    private String response;
    private String request;


    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getHttpStatus() {
        return HttpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        HttpStatus = httpStatus;
    }

    public String getHttpMethod() {
        return HttpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        HttpMethod = httpMethod;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

      public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    public String toString(){
        return "status: " +HttpStatus+", method: "+ HttpMethod+", path: "+path+", response: "+response;
    }
}
