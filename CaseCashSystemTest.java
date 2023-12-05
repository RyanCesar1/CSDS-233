import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Test class for the CaseCashSystem class.
 *
 * @author Ryan Cesar Irizarry
 */
public class CaseCashSystemTest {

    /**
     * Tests the sorting of students by name.
     */
    @Test
    public void testSortName() {
        CaseCashSystem cashSystem = new CaseCashSystem();
        cashSystem.init("Diego", 400);
        cashSystem.init("Alexander", 300);
        cashSystem.init("Cesar",350);
        List<String> sortNames = cashSystem.sortName();
        assertEquals("[Alexander, Cesar, Diego]", sortNames.toString());
    }

    /**
     * Tests the sorting of students by balance.
     */
    @Test
    public void testSortBalance() {
        CaseCashSystem cashSystem = new CaseCashSystem();
        cashSystem.init("Alexander", 400);
        cashSystem.init("Diego", 300);
        cashSystem.init("Cesar", 350);
        List<String> sortBalances = cashSystem.sortBalance();
        assertEquals("[Diego, Cesar, Alexander]", sortBalances.toString());
    }

    /**
     * Tests the sorting of an empty list of students by name.
     */
    @Test
    public void testSortNameEmpty() {
        CaseCashSystem cashSystem = new CaseCashSystem();
        List<String> sortNames = cashSystem.sortName();
        assertEquals("[]", sortNames.toString());
    }

    /**
     * Tests the sorting of an empty list of students by balance.
     */
    @Test
    public void testSortBalanceEmpty() {
        CaseCashSystem cashSystem = new CaseCashSystem();
        List<String> sortBalances = cashSystem.sortBalance();
        assertEquals("[]", sortBalances.toString());
     }

    /**
     * Tests the sorting of a list with one element by name.
     */
    @Test
     public void testSortNameOneElement() {
        CaseCashSystem cashSystem = new CaseCashSystem();
        cashSystem.init("Ryan", 200);
        List<String> sortNames = cashSystem.sortName();
        assertEquals("[Ryan]", sortNames.toString());
     }

    /**
     * Tests the sorting of a list with one element by balance.
     */
    @Test
     public void testSortBalanceOneElement() {
        CaseCashSystem cashSystem = new CaseCashSystem();
        cashSystem.init("Cacique", 200);
        List<String> sortBalances = cashSystem.sortBalance();
        assertEquals("[Cacique]", sortBalances.toString());
      }
}
