import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @DESCRIPTION 重写equals、toString、compareto方法
 * @AUTHER administrator zhangna
 * @create 2018-06-06
 */
public class R implements  Comparable {
    int count;
    public R(int count){
        this.count = count;
    }

    @Override
    public String toString() {
        return "R{" +
                "count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        R r = (R) o;

        return new EqualsBuilder()
                .append(count, r.count)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(count)
                .toHashCode();
    }

    @Override
    public int compareTo(Object o) {
        R r = (R) o;
        //自然排序，升序
        return count > r.count ? 1: count < r.count ? -1 : 0;
        //定制排序，例如降序
//        return count > r.count ? -1: count < r.count ? 1 : 0;
    }
}
