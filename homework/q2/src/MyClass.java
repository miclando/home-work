import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * issues
 * the equls compares only one item
 * hash code was not implamented
 * fixing constructor to eliminate null values
 * to string prints just part of the object
 * the to string use string literales
 * removestring here ther is a bug when you dlete an iteam you change the index of the iteam aftrr it
 * as a result you will skip some items.
 * to fix using remove if wcich will iterate over the colection and call remove.
 * if the list is alinked list thsi will be done in o(n)
 * containsNumber to speed up the method we can change the list to map to make it easier to fid matches.
 *
 */
public class MyClass
{
    private Date m_time;
    private String m_name;
    private List<Long> m_numbers;
    private List<String> m_strings;

    public MyClass(Date time, String name, List<Long> numbers, List<String> strings) {
        m_time = time;
        m_name = name;
        m_numbers = numbers;
        m_strings = strings;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if(!(obj instanceof MyClass)){
            return false;
        }
        MyClass myclass= (MyClass)obj;

        if(this.m_name.equals(myclass.m_name)
                && this.m_numbers.equals(myclass.m_numbers)
                && this.m_strings.equals(myclass.m_strings)
                && this.m_time.equals(myclass.m_time) ){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return Objects.hash(m_time, m_name, m_numbers, m_strings);
    }

    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(m_name);
        for (long item : m_numbers) {
            sb.append(" ").append(item);
        }
        sb.append(" ").append(m_name);
        for (String item : m_strings) {
            sb.append(" ").append(item);
        }
        return sb.toString();
    }

    public void removeString(String str) {
        m_strings.removeIf((s)->str.equals(str));
    }

    public boolean containsNumber(long number) {
        for (long num : m_numbers) {
            if (num == number) {
                return true;
            }
        }
        return false;
    }

    public boolean isHistoric() {
        return m_time.before(new Date());

    }

}
