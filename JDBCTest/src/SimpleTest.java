import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SimpleTest {

	public static void main(String[] args) throws Exception {
		// jdbc driver class loading (driver가 자신의 객체를 driver manager에 등록)
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		// connection 객체 생성/ 획득 => java == mysql 연결 구조 (인증을 거쳐야 함)

		Connection con=
				DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8",
						"root", "1234");
		
		// connection 객체를 통해 preparedStatement (statement )객체 생성 => SQL문 수행
		
//		PreparedStatement pstmt=con.prepareStatement("select count(*) cnt from customer");
		
		// task 1 : 등록 / 수정/ 삭제 => 정수로 결과를 받음 (정수는 영향을 받은 row수 return)
		
//		#1. 등록
//		PreparedStatement pstmt=con.prepareStatement("insert into customer values (3, '삼길동')");
//		int ret=pstmt.executeUpdate();
//		System.out.println(ret);

//		#2. 수정
//		PreparedStatement pstmt=con.prepareStatement("update customer set customer_nm='사길동' where customer_id=?");
//		pstmt.setInt(1, 3); // 1번 째 물음표를 3으로 바꾸어라
//		int ret=pstmt.executeUpdate();
//		System.out.println(ret);

//		#3. 삭제
//		PreparedStatement pstmt=con.prepareStatement("delete from customer where customer_id=?");
//		pstmt.setInt(1, 3); 
//		int ret=pstmt.executeUpdate();
//		System.out.println(ret);
		
		
		// task 2 : 조회 (목록, 상세조회) => resultset 객체 통해 결과를 받고, 그걸 이용해서 출력
		
//		PreparedStatement pstmt=con.prepareStatement("select count(*) cnt from customer");
//		ResultSet rset=pstmt.executeQuery();
//		if ( rset.next() ) {
//			System.out.println(rset.getInt("cnt"));
//		}
		
		
		PreparedStatement pstmt=con.prepareStatement("select * from product");
		ResultSet rset=pstmt.executeQuery();
		while ( rset.next() ) {
			System.out.println(rset.getInt("product_id")+" / " + 
							   rset.getString("product_nm") +" / " + 
							   rset.getInt("product_price"));
		}
		
		
		rset.close();
		pstmt.close();
		con.close();
		
		
		// ResultSet, PreparedStatement, Connection 순으로 자원을 반납  (특히 connection)
		
		// 위 모든 과정에서 MySQL 관련 작업 중 오류가 발생하면 MySQ은 SQLException 객체를 발생
	}

}
