import com.library.util.DBUtil;
import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        try (Connection con = DBUtil.getConnection()) {
            System.out.println("✅ MySQL Connected Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
