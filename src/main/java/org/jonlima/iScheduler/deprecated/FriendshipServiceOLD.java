//package org.jonlima.iScheduler.deprecated;
//
//import org.jonlima.iScheduler.model.Friendship;
//import org.jonlima.iScheduler.repository.FriendshipRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class FriendshipService {
//
//    private final FriendshipRepository friendshipRepository;
//
//    @Autowired
//    public FriendshipService(FriendshipRepository friendshipRepository){
//        this.friendshipRepository = friendshipRepository;
//    }
//
//    //CREATE
//    public Friendship createFriendship(Friendship friendship) {
//        return friendshipRepository.save(friendship);
//    }
//    //READ
//    public List<Friendship> getAllFriendships() {
//        return friendshipRepository.findAll();
//    }
//    public Optional<Friendship> getFriendshipById(Long id) {
//        return friendshipRepository.findById(id);
//    }
//    //UPDATE
//    public Friendship updateFriendship(Long id, Friendship friendship) {
//        Optional<Friendship> existingFriendshipOptional = friendshipRepository.findById(id);
//        if (existingFriendshipOptional.isPresent()) {
//            Friendship existingFriendship = existingFriendshipOptional.get();
//            existingFriendship.setUser1(friendship.getUser1());
//            existingFriendship.setUser2(friendship.getUser2());
//            return friendshipRepository.save(existingFriendship);
//        } else {
//            throw new IllegalArgumentException("Friendship not found with id: " + id);
//        }
//    }
//    //DELETE
//    public void deleteFriendship(Long id) {
//        friendshipRepository.deleteById(id);
//    }
//}
