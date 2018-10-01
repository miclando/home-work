import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * issues:
 * 1. the equals compares only one item
 * 2. hash code was not implemented
 * 3. fixing constructor to eliminate null values
 * 4. tostring prints just part of the object
 * 5. the tostring use string literals
 * 6. removestring here there is a bug when you delete an item you change the index of the item after it
 *    as a result you will skip some items.
 *    to fix this i add the use of removeif wcich will iterate over the collection and call remove.
 * 7. if the list is a linked list this will be done in o(n)
 * 8. containsNumber to speed up the method we can change the list to map to make it easier to fid matches.
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
        m_strings.removeIf((s)->s.equals(str));
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
