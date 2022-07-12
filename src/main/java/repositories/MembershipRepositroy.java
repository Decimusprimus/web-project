package repositories;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.Membership;
import model.MembershipType;

public class MembershipRepositroy extends Repository<Membership, UUID> {

	public MembershipRepositroy(String path) {
		super(path,new TypeToken<List<Membership>>() {}.getType(), new Gson());
		// TODO Auto-generated constructor stub
	}
	
	public Membership createNew(int option, UUID userId) {
		ArrayList<Membership> collection = (ArrayList<Membership>) getAll();
		for(Membership m : collection) {
			if(m.getCustomerId().equals(userId) && m.getValidUntil().compareTo(LocalDate.now()) <0 ) {
				m.setStatus(false);
			}
		}
		saveAll(collection);
		Membership membership = null;
		switch (option) {
		case 1:
			membership = new Membership(UUID.randomUUID(), MembershipType.MONTH, LocalDate.now(), LocalDate.now().plusMonths(1), 2600, true, 12, false, userId);
			create(membership);
			return membership;
		case 2:
			membership = new Membership(UUID.randomUUID(), MembershipType.MONTH, LocalDate.now(), LocalDate.now().plusMonths(1), 3000, true, 16, false, userId);
			create(membership);
			return membership;
		case 3:
			membership = new Membership(UUID.randomUUID(), MembershipType.MONTH, LocalDate.now(), LocalDate.now().plusMonths(1), 4400, true, 30, false, userId);
			return membership;
		case 4:
			membership = new Membership(UUID.randomUUID(), MembershipType.THREE_MONTHS, LocalDate.now(), LocalDate.now().plusMonths(3), 7000, true, 12*3, false, userId);
			create(membership);
			return membership;
		case 5:
			membership = new Membership(UUID.randomUUID(), MembershipType.THREE_MONTHS, LocalDate.now(), LocalDate.now().plusMonths(3), 8000, true, 16*3, false, userId);
			create(membership);
			return membership;
		case 6:
			membership = new Membership(UUID.randomUUID(), MembershipType.YEAR, LocalDate.now(), LocalDate.now().plusYears(1), 15000, true, 365, false, userId);
			create(membership);
			return membership;
		default:
			return membership;
		}
	}
	
	public Membership checkForMembership(UUID id) {
		ArrayList<Membership> collection = (ArrayList<Membership>) getAll();
		for(Membership m : collection) {
			if(m.getCustomerId().equals(id)) {
				if(m.isStatus() && m.getValidUntil().compareTo(LocalDate.now()) > 0 && m.getCounter() > 0) {
					return m;
				}
			}
		}
		return null;
	}
	

}
