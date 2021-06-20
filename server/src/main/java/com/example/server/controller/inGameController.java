package com.example.server.controller;

import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import org.springframework.http.HttpStatus;
import org.springframework.util.concurrent.SuccessCallback;
import java.lang.Math;
import org.springframework.web.server.ResponseStatusException;


import java.io.*;
import java.util.HashMap;

@RestController
@RequestMapping("/ingame")
public class inGameController
{
    // @Autowired db 용
    @PostMapping
    public String[][] playGame(@RequestBody HashMap<String, String> map, @RequestHeader HashMap<String, String> map2) throws Exception{

        //nextKey me = new nextKey();
//
//        key.makeNew();
        //me.setMyKey(map.get("key")); // lomboc 내장 게터 세터 함수 이용
        // 파일에 저장 -> 데이터베이스에 저장하는 코드

        if(moveMap(map.get("key"))==true)
            return new String[][]{ {"W","e","l","l"}, {"D","o","n","e","!"},{"✿","✿","✿","✿","✿","✿","✿","✿","✿"}};

        System.out.println(map.get("key"));
        return GameMap.mp;
    }
    public boolean moveMap(String ky)throws Exception{
        int dir = GameMap.Dir.get(ky);
        int ny = GameMap.y + GameMap.Dy[dir];
        int nx = GameMap.x + GameMap.Dx[dir];
        /*
        for i in range(0,bound,2):
        mp[-4][i+base] = power[i//2]
        mp[-4][i+base] = 'o' if state[i//2] == True else 'x'
         */
        for(int i = 0; i < GameMap.bound; i= i + 2)
        {
            if(GameMap.state[i/2]==true)
                GameMap.mp[GameMap.r-4][i + GameMap.base] = "o";
            else GameMap.mp[GameMap.r-4][i + GameMap.base] = "x";
        }
        int nxt = chk(ny, nx, dir);
        boolean blocked = false;
        if(nxt > 0)
        {
            if(GameMap.state[nxt-1] == false || (nxt > 3) ) {
                blocked = true;
            }
            else{
                GameMap.recent = nxt;
                int sy = ny + nxt * GameMap.Dy[dir];
                int sx = nx + nxt * GameMap.Dx[dir];
                GameMap.mp[sy][sx] = "*";
                GameMap.state[nxt-1] = false;
            }
        }

        else{
            GameMap.mp[GameMap.y][GameMap.x] = " ";
            GameMap.mp[ny][nx] = "✎";
            GameMap.y = ny;
            GameMap.x = nx;
        }

        for(int i = 1; i < 4; ++i)
        {
            if(i != GameMap.recent && GameMap.state[i-1] == false){
                GameMap.state[i-1] = true;
                break;
            }
        }

        if(blocked == false) {
            GameMap.mp[GameMap.y][GameMap.x] = " ";
            GameMap.mp[ny][nx] = "✎";
            GameMap.y = ny;
            GameMap.x = nx;
        }
        for(int i = 0; i < GameMap.bound; i= i + 2)
        {
            if(GameMap.recent-1 ==i/2)
                GameMap.mp[GameMap.r-4][i + GameMap.base] = "x";
            else GameMap.mp[GameMap.r-4][i + GameMap.base] = "o";
        }
        if(goal() == true) return true;
        return false;
    }

    public boolean goal(){
        if( (GameMap.y ==GameMap.r-1) && (GameMap.x ==GameMap.r-1)) return true;
        return false;
    }
    public int chk(int y, int x, int d)throws Exception{
        int ny = y;
        int nx = x;
        int cnt = 0;
        while(GameMap.mp[ny][nx] == "*")
        {
            cnt++;
            ny += GameMap.Dy[d];
            nx += GameMap.Dx[d];

        }
        return cnt;
    }

}
