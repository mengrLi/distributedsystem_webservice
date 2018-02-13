package Domain;

public enum CampusName{
    DORVAL("Droval Campus", "DVL", "dorval", 1099, 5850),
    KIRKLAND("Kirkland Campus", "KKL", "kirkland", 1099, 5851),
    WESTMOUNT("Westmount Campus", "WST", "westmount", 1099, 5852);

    public String name;
    public String abrev;
    public int port;
    public String serverName;
    public int inPort;

    CampusName(String name, String abrev, String serverName, int port, int inPort){
        this.name = name;
        this.abrev = abrev;
        this.port = port;
        this.serverName = serverName;
        this.inPort = inPort;
    }

    public static CampusName getCampusName(String abrev) {
        for (CampusName campusName : CampusName.values()) {
            if (campusName.abrev.equals(abrev)) return campusName;
        }
        return null;
    }
    public static int determinePort(String campusOfInterestAbrev) {
        CampusName ret = getCampusName(campusOfInterestAbrev);
        if (ret != null) return ret.inPort;
        System.err.println("Campus invalid");
        return -1;
    }


}




