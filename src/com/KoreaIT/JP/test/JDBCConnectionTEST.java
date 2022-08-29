package com.KoreaIT.JP.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC 테스트 코드
public class JDBCConnectionTEST {
	public static void main(String[] args) {
		Connection conn = null;

		try { // 예외 처리 방식
			Class.forName("com.mysql.jdbc.Driver"); // 실제 일하는 주체
			String url = "jdbc:mysql://127.0.0.1:3306/JDBCTest?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull"; 
			// 목적지
			// 로컬 호스트 : 127.0.0.1:3306
			// 로컬 호스트 뒤부터는 옵션 수정이 필요함(SQLyog 에 있는 데이터베이스와 연결 위해서 수정 보완)
			// JDBCTest 여기에서 불러올 거다.
			// 언어 설정 : useUnicode=true&characterEncoding=utf8
			// 연결 실패시 재연결 : autoReconnect=true
			// 위치 및 시간 관련 : serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull
			// Seoul : GMT 표준시 관련 설정

			conn = DriverManager.getConnection(url, "root", ""); // SQLyog에 설정한 URL 아이디 비번 인자로 받기 (현재는 비번 설정안함)
			System.out.println("연결 성공!");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패"); // 실행은 되나, 중간에 에러나서 멈춤
		} catch (SQLException e) {
			System.out.println("에러: " + e); // 이거는 실행되지 못하는 빨간줄 상태
		} finally { // 예외가 발생하던 안하던 무조건 실행
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
} 