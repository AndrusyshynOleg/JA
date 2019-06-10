package logos.lesson17.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import logos.lesson17.DAO.UnivercityRepository;
import logos.lesson17.domain.Univercity;
import logos.lesson17.service.UnivercityService;

@Service
public class UnivercityServiceImpl implements UnivercityService{

	@Autowired
	private UnivercityRepository univercityRepository;
	
	@Override
	public Univercity save(Univercity univercity) {
		return univercityRepository.saveAndFlush(univercity);
		
	}

	@Override
	public Univercity findById(Long id) {
		return univercityRepository.getOne(id);
	}

	@Override
	public Univercity update(Univercity univercity) {
		return univercityRepository.save(univercity);
	}

	@Override
	public void deleteById(Long id) {
		univercityRepository.deleteById(id);
	}

	@Override
	public List<Univercity> findAll() {
		return univercityRepository.findAll();
	}

	@Override
	public List<Univercity> findByName(String name) {
		return univercityRepository.findByName(name);
	}

	@Override
	public List<Univercity> findByAddress(String address) {
		return univercityRepository.findByName(address);
	}

	

}
