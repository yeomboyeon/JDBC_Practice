package com.KoreaIT.JP.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// JDBC 테스트 코드
public class JDBCInsertTEST {
	public static void main(String[] args) {
		
		Connection conn = null;
		
		PreparedStatement pstmt = null;  // INSERT문 실행 위해 추가
		
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

			// 연결이 성공 되었다면 해당 쿼리를 보여주겠다.
			// SQLyog INSERT 쿼리문 복사
			
			String sql = "INSERT INTO article"; 
			sql += " SET regDate = NOW(),"; // 문법 오류 방지를 위해서 SET 앞에 한칸 띄우기(실행시 article 와 SET 가 합해져서 인식되어 띄워야 함)
			sql += "updateDate = NOW(),";
			sql += "title = CONCAT('제목',RAND()),";
			sql += "`body` = CONCAT('내용',RAND());";
			
			pstmt = conn.prepareStatement(sql); // INSERT문 실행 위해 추가
			
			// pstmt.executeUpdate(); // INSERT문 실행 위해 추가
			int affectedRows = pstmt.executeUpdate(); // SQLyog 결과창에 보여지는 문법 표기 위해서 변수로 저장
			
			System.out.println(affectedRows + "열에 적용됨"); // SQLyog 결과창에 보여지는 문법 표시 위해서 활용
			
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
			// 규칙 : conn 이라는 자원을 사용했기에 닫아야하고 pstmt 를 사용하기 위해서는 try문을 추가해야 한다.
			try { 
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}