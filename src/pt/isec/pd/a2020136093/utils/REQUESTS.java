package pt.isec.pd.a2020136093.utils;

public class REQUESTS {

    // ENVIO CLIENTE PARA SERVIDOR [GERAL]
    public final static int CLIENT_REQUEST_LOGIN = 11;
    public final static int CLIENT_REQUEST_REGISTER = 12;
    public final static int CLIENT_REQUEST_LOGOUT = 10;


    // ADMIN
    public final static int ADMIN_REQUEST_CREATE_EVENT = 21;
    public final static int ADMIN_REQUEST_EDIT_EVENT = 22;
    public final static int ADMIN_REQUEST_DELETE_EVENT = 23;
    public final static int ADMIN_REQUEST_CHECK_EVENTS = 24;
    public final static int ADMIN_REQUEST_GENERATE_CODE = 25;
    public final static int ADMIN_REQUEST_CHECK_PRESENCES_EVENT = 26;
    public final static int ADMIN_REQUEST_DELETE_PRESENCE = 27;
    public final static int ADMIN_REQUEST_ADD_PRESENCE = 28;


    // ALUNO
    public final static int CLIENT_REQUEST_EDIT_DATA = 13;
    public final static int CLIENT_REQUEST_SUBMIT_CODE = 14;
    public final static int CLIENT_REQUEST_CHECK_PRESENCES = 15;
}
