package logos.lesson17.service;

import java.util.List;

import logos.lesson17.domain.Univercity;

public interface UnivercityService {
	
	Univercity save(Univercity univercity);
	
	Univercity findById(Long id);
	
	Univercity update(Univercity univercity);
	
	void deleteById(Long id);
	
	List<Univercity> findAll();
	
	List<Univercity> findByName(String name);
	
	List<Univercity> findByAddress(String address);
		
	
}
