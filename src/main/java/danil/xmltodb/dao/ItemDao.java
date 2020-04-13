package danil.xmltodb.dao;

import danil.xmltodb.model.db.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ItemDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void saveAll(List<Item> items) {
        String sql = "INSERT INTO ITEM VALUES (:id, :containedIn, :color) ON CONFLICT (ID) DO NOTHING";
        SqlParameterSource[] params = items.stream()
                .map(item -> {
                    MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                            .addValue("id", item.getId())
                            .addValue("containedIn", item.getContainedIn());
                    if (Objects.nonNull(item.getColor())) {
                        sqlParameterSource.addValue("color", item.getColor());
                    } else {
                        sqlParameterSource.addValue("color", "");
                    }
                   return sqlParameterSource;
                })
                .collect(Collectors.toList()).toArray(new SqlParameterSource[]{});
        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }

    public List<Integer> findAllByColorAndContainedIn(String color, Integer containedIn) {
        String sql = "WITH RECURSIVE R AS ( " +
                "    SELECT ID, CONTAINED_IN " +
                "    FROM BOX " +
                "    WHERE CONTAINED_IN = :containedIn " +
                "    OR ID = :containedIn " +
                " " +
                "    UNION " +
                " " +
                "    SELECT BOX.ID, BOX.CONTAINED_IN " +
                "    FROM BOX " +
                "             JOIN R " +
                "                  ON BOX.CONTAINED_IN = R.ID " +
                ") " +
                "SELECT ID FROM ITEM WHERE COLOR = :color AND CONTAINED_IN IN (SELECT ID FROM R)";
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("containedIn", containedIn)
                .addValue("color", color);
        return namedParameterJdbcTemplate.queryForList(sql, sqlParameterSource, Integer.class);
    }
}
