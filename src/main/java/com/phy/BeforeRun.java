package com.phy;

import com.phy.A_DefaultValues.ServerValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BeforeRun {

    public static String beforeRun() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ServerValue.ServerMode[] serverList = ServerValue.ServerMode.values();
        int count = 1;
        int rslt = 0;
        System.out.println("================================================");
        System.out.println("서버 가동을 시작합니다.");
        System.out.println("시작할 버전을 선택해주세요.");
        for( ServerValue.ServerMode s : serverList ){

            System.out.println(count+". "+s.serverName);

            count++;

        }
        System.out.println("입력 : ");
        rslt = Integer.parseInt(br.readLine());
        System.out.println("================================================");

        return serverList[rslt].chooseProperties;
    }

}
