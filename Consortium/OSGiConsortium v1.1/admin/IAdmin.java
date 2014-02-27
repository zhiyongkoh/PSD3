package admin;

import entity.User;

public interface IAdmin {
	void setRoomToSlot() throws Exception;

	User displayMenu(User user) throws Exception;
}
