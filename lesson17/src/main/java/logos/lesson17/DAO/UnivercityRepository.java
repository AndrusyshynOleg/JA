package logos.lesson17.DAO;

import java.util.List;
import java.util.stream.Stream;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import logos.lesson17.domain.Univercity;

public interface UnivercityRepository extends JpaRepository<Univercity, Long>, CrudRepository<Univercity, Long>{
	
	List<Univercity> findByName(String name);
	
	List<Univercity> findByAddress(String address);
		
}
