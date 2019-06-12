package logos.lesson18.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import logos.lesson18.dao.ParticipantRepo;
import logos.lesson18.domain.Participant;

@Service
public class ParticipantService {
	
	@Autowired
	private ParticipantRepo Repo;
	
	public List<Participant> findAll() {
		return Repo.findAll();
	}

	public Participant findOne(int id) {
		return Repo.findOne(id);
	}

	public void save(Participant participant) {
		Repo.save(participant);
	}

	public void delete(int id) {
		Repo.delete(id);
	}
}
