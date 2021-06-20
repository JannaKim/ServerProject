package com.example.server.controller;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
@Data
public class GameMap {
    public static String[][] mp; // 멤버변수는 스태틱 제외한
    public static int r;
    public static int c;
    public static boolean[] state = new boolean[4];
    public static boolean H[][][];
    public static int recent;
    public static int base;
    public static int bound;
    public static String[] power = {"1","2","3"};
    public static int y = 0;
    public static int x = 0;
    public static HashMap<String,Integer> Dir = new HashMap<>();//new에서 타입 파라미터 생략가능
    public static int[] Dy = {0, 1, 0, -1};
    public static int[] Dx = {1, 0, -1, 0};
}
/*

user1, map1, mapping 연결해주는

빌더 패턴:

유저를 만들어 주는 클래스를 유저안에다 넣음
유저에 들어가는



 */