package services;

import java.util.UUID;

import model.Membership;
import repositories.MembershipRepositroy;

public class MembershipService {
	private MembershipRepositroy memebershipRepository;

	public MembershipService(MembershipRepositroy memebershipRepository) {
		super();
		this.memebershipRepository = memebershipRepository;
	}
	
	public Membership createMembership(String option, UUID userId) {
		try {
			int o = Integer.parseInt(option);
			Membership membership = memebershipRepository.createNew(o, userId);
			return membership;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
