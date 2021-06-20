package com.example.server.controller;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.beans.factory.annotation.Autowired; //db용?
import org.springframework.http.HttpStatus;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.*;
import java.lang.Math;
import org.springframework.web.server.ResponseStatusException;


import java.io.*;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/signup")

public class SignUpController
{
    // @Autowired db 용
    @PostMapping
    public String[][] Hello(@RequestBody HashMap<String, String> map, @RequestHeader HashMap<String, String> map2) throws Exception{
        User user = new User(); // 새 유저 아이디 생성

        user.setId(map.get("id")); // lomboc 내장 게터 세터 함수 이용
        user.setPw(map.get("pw"));
        // 파일에 저장 -> 데이터베이스에 저장하는 코드
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./a.txt")));
        writer.write(map.get("id") + '\n');
        writer.write(map.get("pw") + '\n');
        writer.write(map.get("mapsize") + '\n');
        writer.flush();
        writer.close();
        int inp = Integer.parseInt(map.get("mapsize"));
        System.out.println(inp);




        return makeMap(inp);
    }
    public String[][] makeMap(int inp)
    {
        double N = Math.pow(3, inp);
        int n = Integer.parseInt(String.valueOf(Math.round(N)));


        System.out.println(n);
        GameMap.base = n + 4;
        GameMap.bound = 3 * 2;
        GameMap.r = n;
        GameMap.c = n + 15;

        GameMap.Dir.put("d",0);
        GameMap.Dir.put("s",1);
        GameMap.Dir.put("a",2);
        GameMap.Dir.put("w",3);
        GameMap.mp = new String[GameMap.r][GameMap.c];
        //H = [ [ [False] * 4 for _ in range(n)] for __ in range(n)]
        GameMap.H = new boolean[n][n][4];
        GameMap.recent = -1;
        for(int i = 0; i < GameMap.r; ++i)
        {
            for(int j =0; j < GameMap.c; ++j) {
                GameMap.mp[i][j] = " ";
            }
        }
        //String printPowerString = new String("1 2 3");
        //String[] printPower;
        //printPower = printPowerString.split("");
        GameMap.mp[n -5][GameMap.base] = "1";
        GameMap.mp[n -5][GameMap.base+2] = "2";
        GameMap.mp[n -5][GameMap.base+4] = "3";
        GameMap.state[0] = true;
        GameMap.state[1] = true;
        GameMap.state[2] = true;


        rec(n/2, n/2, n/2);
        GameMap.mp[0][0] = "✎";
        GameMap.mp[GameMap.r-1][GameMap.r-1]= "♡";
        for(int i = 0; i < GameMap.r; ++i)
        {
            for(int j =0; j < GameMap.c; ++j) {
                System.out.print(GameMap.mp[i][j]);
            }
            System.out.println();
        }

        return GameMap.mp;

    }

    public void rec(int y, int x, int k)
    {
        if(k == 1) return ;

        for(int i = 0; i < GameMap.r; ++i)
        {
            for(int j =0; j < GameMap.r; ++j)
            {
                if(Math.abs(y - i) + Math.abs(x - j) ==k) GameMap.mp[i][j] = "*";

            }

            int nxt = k / 2;
            rec(y-nxt , x - nxt, nxt);
            rec(y-nxt , x + nxt, nxt);
            rec(y+nxt , x - nxt, nxt);
            rec(y+nxt , x + nxt, nxt);


        }

    }


//    @GetMapping("/seealluserinfo")
//    public User getREP(@RequestHeader HashMap<String,String> map) throws Exception
//    {
//        User user = new User();
//        if(map.get("authorization").equals(key.getKey()) == false) {
//            throw new ResponseStatusException(
//                    HttpStatus.UNAUTHORIZED, "GET OUT!!!"
//            );
//        }
//        //파일에서 읽어오기 > 데이터 베이스에서 읽어오기
//        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./a.txt")));
//        String email = reader.readLine();
//        String pw = reader.readLine();
//        user.setEmail(email);
//        user.setPw(pw);
//        return user;
//    }

}
