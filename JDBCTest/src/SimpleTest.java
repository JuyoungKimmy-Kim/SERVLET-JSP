import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SimpleTest {

	public static void main(String[] args) throws Exception {
		// jdbc driver class loading (driver�� �ڽ��� ��ü�� driver manager�� ���)
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		// connection ��ü ����/ ȹ�� => java == mysql ���� ���� (������ ���ľ� ��)

		Connection con=
				DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8",
						"root", "1234");
		
		// connection ��ü�� ���� preparedStatement (statement )��ü ���� => SQL�� ����
		
//		PreparedStatement pstmt=con.prepareStatement("select count(*) cnt from customer");
		
		// task 1 : ��� / ����/ ���� => ������ ����� ���� (������ ������ ���� row�� return)
		
//		#1. ���
//		PreparedStatement pstmt=con.prepareStatement("insert into customer values (3, '��浿')");
//		int ret=pstmt.executeUpdate();
//		System.out.println(ret);

//		#2. ����
//		PreparedStatement pstmt=con.prepareStatement("update customer set customer_nm='��浿' where customer_id=?");
//		pstmt.setInt(1, 3); // 1�� ° ����ǥ�� 3���� �ٲپ��
//		int ret=pstmt.executeUpdate();
//		System.out.println(ret);

//		#3. ����
//		PreparedStatement pstmt=con.prepareStatement("delete from customer where customer_id=?");
//		pstmt.setInt(1, 3); 
//		int ret=pstmt.executeUpdate();
//		System.out.println(ret);
		
		
		// task 2 : ��ȸ (���, ����ȸ) => resultset ��ü ���� ����� �ް�, �װ� �̿��ؼ� ���
		
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
		
		
		// ResultSet, PreparedStatement, Connection ������ �ڿ��� �ݳ�  (Ư�� connection)
		
		// �� ��� �������� MySQL ���� �۾� �� ������ �߻��ϸ� MySQ�� SQLException ��ü�� �߻�
	}

}
