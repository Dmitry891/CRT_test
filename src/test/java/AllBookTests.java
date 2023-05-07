import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GetBookTest.class,
        AddBookTest.class,
        UpdateBookTest.class,
        DeleteBookTest.class,
})
public class AllBookTests {

}