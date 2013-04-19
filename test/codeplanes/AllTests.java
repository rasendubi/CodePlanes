package codeplanes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
      BattleCollectorTest.class
    , BattleTest.class
    , BulletTest.class
    , PlaneTest.class
    , SimulationTest.class
    , WorldTest.class
})
public class AllTests {

}
