package com.phy.A_DefaultValues;

public class ServerValue {

    public enum ServerMode{
        개발계("개발계","dev"), 운영계("운영계","prod");

        public final String serverName;
        public final String chooseProperties;

        ServerMode(String serverName, String chooseProperties){
            this.serverName = serverName;
            this.chooseProperties = chooseProperties;
        }
    }

}
