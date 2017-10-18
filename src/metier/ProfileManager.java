package metier;

import java.util.List;

public class ProfileManager {
		private List<Profil> profiles;

		public List<Profil> getProfiles() {
			return profiles;
		}

		public void setProfiles(List<Profil> profiles) {
			this.profiles = profiles;
		}

		@Override
		public String toString() {
			return "ProfileManager [profiles=" + profiles + "]";
		}
		
}
