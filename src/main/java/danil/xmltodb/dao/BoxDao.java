package danil.xmltodb.dao;

import danil.xmltodb.model.db.Box;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BoxDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void saveAll(List<Box> boxes) {
        String sql = "INSERT INTO BOX VALUES (:id, :containedIn) ON CONFLICT (ID) DO NOTHING";
        SqlParameterSource[] params = boxes.stream()
                .map(box -> new MapSqlParameterSource()
                        .addValue("id", box.getId())
                        .addValue("containedIn", box.getContainedIn()))
                .collect(Collectors.toList()).toArray(new SqlParameterSource[]{});
        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }
}
