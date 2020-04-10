package danil.xmltodb.dao;

import danil.xmltodb.model.db.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("select id from Item where color=:color and containedIn = :containedIn")
    List<Integer> findAllByColorAndContainedIn(@Param("color") String color,
                                            @Param("containedIn") Integer containedIn);

}
