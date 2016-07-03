package info.shiyi;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;

//import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service for processing Persons.
 * <p>
 * 关于Spring JDBC 和 JdbcTemplate
 * see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/jdbc.html
 * <p>
 * 关于transactions, see http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/transaction.html
 */

@Service("personService")
@Transactional
public class PersonService {

    //protected static Logger logger = Logger.getLogger("service");

    private JdbcTemplate jdbcTemplate;

    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     *检索所有的Person
     */
    public List<Person> getAll() {
        //logger.debug("Retrieving all persons");

        String sql = "select id, first_name, last_name, money from person";

        // Maps a SQL result to a Java object
        RowMapper<Person> mapper = new RowMapper<Person>() {
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setFirstName(rs.getString("first_name"));
                person.setLastName(rs.getString("last_name"));
                person.setMoney(rs.getDouble("money"));
                return person;
            }
        };

        return jdbcTemplate.query(sql, mapper);
    }

    /**
     * 新增person
     */
    public void add(String firstName, String lastName, Double money) {
        //logger.debug("Adding new person");

        String sql = "insert into person(first_name, last_name, money) values " +
                "(:firstName, :lastName, :money)";

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("firstName", firstName);
        parameters.put("lastName", lastName);
        parameters.put("money", money);

        // Save
        jdbcTemplate.update(sql, parameters);

    }

    /**
     * 删除指定Person
     */
    public void delete(Integer id) {
        //logger.debug("Deleting existing person");

        String sql = "delete from person where id = ?";

        Object[] parameters = new Object[] {id};

        jdbcTemplate.update(sql, parameters);
    }

    /**
     * Edit指定的Person
     */
    public void edit(Integer id, String firstName, String lastName, Double money) {
        //logger.debug("Editing existing person");

        String sql = "update person set first_name = :firstName, " +
                "last_name = :lastName, money = :money where id = :id";

        // Assign values to parameters
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id", id);
        parameters.put("firstName", firstName);
        parameters.put("lastName", lastName);
        parameters.put("money", money);

        // Edit
        jdbcTemplate.update(sql, parameters);

    }

}
