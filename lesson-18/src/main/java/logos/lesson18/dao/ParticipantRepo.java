package logos.lesson18.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import logos.lesson18.domain.Level;
import logos.lesson18.domain.Participant;

@Repository
public class ParticipantRepo {
	
	private List<Participant> participants = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		Participant pat1 = new Participant();
		pat1.setName("Roma");
		pat1.setEmail("Roma@gmail.com");
		pat1.setLevel(Level.L2);
		pat1.setPrimarySkill("reading");
		pat1.setId(1);
		
		Participant pat2 = new Participant();
		pat2.setName("Taras");
		pat2.setEmail("Taras@gmail.com");
		pat2.setLevel(Level.L5);
		pat2.setPrimarySkill("reading, coding");
		pat2.setId(2);
		
		Participant pat3 = new Participant();
		pat3.setName("Sasha");
		pat3.setEmail("Sasha@gmail.com");
		pat3.setLevel(Level.L4);
		pat3.setPrimarySkill("reading, testing, swiming");
		pat3.setId(3);
		
		
		participants.add(pat1);
		participants.add(pat2);
		participants.add(pat3);
	}
	
	public List<Participant> findAll(){
		return participants;
	}
	
	public Participant findOne(int id) {
		return participants.stream().filter(participant -> participant.getId() == id).reduce((a, b) ->{
			throw new IllegalStateException("multi elements: " + a + ", " + b);
		}).get();
	}
	
	public void save(Participant part) {
		Participant partToUpd = null;
		
		if(part.getId() != null) {
			partToUpd = findOne(part.getId());
			int partIndex = participants.indexOf(partToUpd);
			partToUpd.setName(part.getName());
			partToUpd.setEmail(part.getEmail());
			partToUpd.setLevel(part.getLevel());
			partToUpd.setPrimarySkill(part.getPrimarySkill());
			participants.set(partIndex, partToUpd);
		}else {
			//save
			part.setId(participants.size());
			participants.add(part);
		}
	}
	
	public void delete(int id) {
		Iterator<Participant> iter = participants.iterator();
		while (iter.hasNext()) {
			if (iter.next().getId() == id) {
				iter.remove();
			}
	}
	}
}
