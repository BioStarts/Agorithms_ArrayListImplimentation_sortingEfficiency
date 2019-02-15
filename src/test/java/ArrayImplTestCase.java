import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class ArrayImplTestCase {

    public static final int INVALID_VALUE = 777;

    @Test
    public void test_init_array(){
        Array<Integer> array = new ArrayImpl<>();
        array.add(1);
        array.add(2);
        Assert.assertThat(array.getSize(), Is.is(2));
        Assert.assertThat(array.get(0), Is.is(1));
        Assert.assertThat(array.get(1), Is.is(2));
    }

    @Test
    public void test_init_array_fixed_size(){
        Array<Integer> array = new ArrayImpl<>(2);
        array.add(1);
        array.add(2);
        array.add(3);
        Assert.assertThat(array.getSize(), Is.is(3));
        Assert.assertThat(array.get(0), Is.is(1));
        Assert.assertThat(array.get(1), Is.is(2));
        Assert.assertThat(array.get(2), Is.is(3));
    }

    @Test
    public void test_search(){
        Array<Integer> array = new ArrayImpl<>();
        array.add(10);
        array.add(20);
        array.add(30);

        Assert.assertThat(array.get(0), Is.is(10));
        Assert.assertThat(array.get(1), Is.is(20));
        Assert.assertThat(array.get(2), Is.is(30));


        Assert.assertFalse(array.contains(INVALID_VALUE));

        Assert.assertThat(array.indexOf(INVALID_VALUE), Is.is(-1));
    }

    @Test
    public void test_remove_by_index() {
        Array<Integer> array = new ArrayImpl<>();
        array.add(10);
        array.add(20);
        array.add(30);

        array.remove(0);

        Assert.assertThat(array.getSize(), Is.is(2));
        Assert.assertThat(array.get(0), Is.is(20));
        Assert.assertThat(array.get(1), Is.is(30));

    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void test_remove_by_invalid_index() {
        Array<Integer> array = new ArrayImpl<>();
        array.add(10);
        array.add(20);
        array.add(30);

        array.remove(50);

    }

    @Test
    public void test_remove_by_value() {
        Array<Integer> array = new ArrayImpl<>();
        array.add(10);
        array.add(20);
        array.add(30);

        Assert.assertFalse(array.remove(Integer.valueOf(INVALID_VALUE)));
        Assert.assertTrue(array.remove(Integer.valueOf(20)));


        Assert.assertThat(array.getSize(), Is.is(2));
        Assert.assertThat(array.get(0), Is.is(10));
        Assert.assertThat(array.get(1), Is.is(30));

    }

}
