package org.jonlima.iScheduler.service.impl;

import org.jonlima.iScheduler.model.Users;
import org.jonlima.iScheduler.model.Friendship;
import org.jonlima.iScheduler.repository.FriendshipRepository;
import org.jonlima.iScheduler.service.FriendshipService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FriendshipServiceImpl implements FriendshipService {
    private FriendshipRepository friendshipRepository;

    public FriendshipServiceImpl(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public List<Users> findFriendsByUserId(long id) {
        return friendshipRepository.findFriendsByUserId(id);
    }

    @Override
    public void addFriend(Users users, Users friend) {
        Friendship friendship = new Friendship();
        friendship.setUsers1(users);
        friendship.setUsers2(friend);
        friendshipRepository.save(friendship);
    }

    @Override
    public void removeFriend(Users users, Users friend) {
        Friendship friendship = friendshipRepository.findByUsers1AndUsers2(users, friend);
        System.out.println(friendship);
        if (friendship != null) {
            friendshipRepository.delete(friendship);
        } else {
            // If the friendship does not exist, you may want to handle this case accordingly
            //throw new FriendshipNotFoundException("Friendship not found between users " + users.getId() + " and friend " + friend.getId());
        }
    }

}
