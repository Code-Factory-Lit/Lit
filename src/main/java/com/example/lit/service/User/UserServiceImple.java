package com.example.lit.service.User;

import com.example.lit.domain.dao.user.AlertDAO;
import com.example.lit.domain.dao.user.FollowDAO;
import com.example.lit.domain.dao.message.MessageDAO;
import com.example.lit.domain.dao.user.UserDAO;
import com.example.lit.domain.dao.user.UserFileDAO;
import com.example.lit.domain.dao.user.achievement.AchievementDAO;
import com.example.lit.domain.vo.SearchDTO;
import com.example.lit.domain.vo.user.*;
import com.example.lit.domain.vo.user.FollowVO;
import com.example.lit.domain.vo.messsage.MessageVO;
import com.example.lit.domain.vo.user.UserFileVO;
import com.example.lit.domain.vo.user.UserVO;
import com.example.lit.domain.vo.user.achievement.AchievementVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImple implements UserService{
    private final AchievementDAO achievementDAO;
    private final FollowDAO followDAO;
    private final MessageDAO messageDAO;
    private final UserDAO userDAO;
    private final UserFileDAO userFileDAO;
    private final AlertDAO alertDAO;

    @Override
    public void kakaoRegister(UserVO userVO) {;}

    @Override
    public boolean dbEmailCheck(String email) {
        return userDAO.dbEmailCheck(email) != 1;
    }

    @Override
    public boolean dbNicknameCheck(String nickname) {
        return userDAO.dbNicknameCheck(nickname) != 1;
    }

    @Override
    public int MyReviewCnt(Long userNumber) { return userDAO.MyReviewCnt(userNumber); }

    @Override
    public int MyFollowerCnt(Long userNumber) { return userDAO.MyFollowerCnt(userNumber); }

    @Override
    public int MyFollowingCnt(Long userNumber) { return userDAO.MyFollowingCnt(userNumber); }

    @Override
    public List<UserVO> ModalFollower(Long userNumber) { return userDAO.ModalFollower(userNumber); }

    @Override
    public List<UserVO> ModalFollowing(Long userNumber) { return userDAO.ModalFollowing(userNumber); }

    @Override
    public void removeFollower(Long followerNumber, Long followingNumber) { userDAO.removeFollower(followerNumber, followingNumber); }

    @Override
    public boolean dbOldPwCheck(String password, Long userNumber) { return userDAO.dbOldPwCheck(password, userNumber); }

    @Override
    public UserVO getChangePwInfo(Long userNumber) { return userDAO.getChangePwInfo(userNumber); }

    @Override
    public UserVO login(String email, String pw) {
        return userDAO.login(email, pw);
    }

    @Override
    public UserVO read(Long userNumber) { return userDAO.read(userNumber); }

    @Override
    public boolean adminLogin(String email, String password) {
        return userDAO.adminLogin(email, password);
    }

    @Override
    public void remove(Long userNumber) {
        userDAO.remove(userNumber);
    }

    @Override
    public void modify(UserVO userVO) { userDAO.modify(userVO); }

    @Override
    public void modifyPw(Long userNumber, String newPassword) { userDAO.modifyPw(userNumber, newPassword); }

    @Override
    public void modifyMedal(UserVO userVO) {

    }

    @Override
    public void registerImg(UserFileVO userFileVO) {

    }

    @Override
    public void removeImg(Long userNumber) {

    }

    @Override
    public void modifyImg(UserFileVO userFileVO) {

    }

    @Override
    public UserFileVO getImg(Long userNumber) {
        return userFileDAO.getImg(userNumber);
    }

    @Override
    public List<UserFileVO> getOldFiles() {
        return null;
    }

    @Override
    public void follow(FollowVO followVO) {
        followDAO.register(followVO);
        AlertVO alertVO = new AlertVO();
        alertVO.setAlertUser(followVO.getFollowingNumber());
        alertVO.setUserNumber(followVO.getFollowerNumber());
        alertVO.setTypeAlert("follow");

        alertDAO.insert(alertVO);
    }

    @Override
    public void removeFollow(FollowVO followVO) {
        followDAO.remove(followVO);

        AlertVO alertVO = new AlertVO();
        alertVO.setAlertUser(followVO.getFollowingNumber());
        alertVO.setUserNumber(followVO.getFollowerNumber());
        alertVO.setTypeAlert("follow");

        alertDAO.remove(alertDAO.getAlertNumber(alertVO));
    }

    @Override
    public int followingCount(FollowVO followVO) {
        return 0;
    }

    @Override
    public int followerCount(FollowVO followVO) {
        return 0;
    }

    @Override
    public void registerMessageRoom(MessageVO messageVO) {

    }

    @Override
    public List<MessageVO> getMessageList(MessageVO messageVO) {
        return null;
    }

    @Override
    public void registerMedal(AchievementVO achievementVO) {

    }

    @Override
    public List<AchievementVO> getMedalList(Long userNumber) {
        return null;
    }

    @Override
    public AchievementVO readMedal(Long userNumber) {
        return null;
    }


    @Override
    public List<UserDTO> userSearch(SearchDTO searchDTO) {
        return userDAO.userSearch(searchDTO);
    }

    @Override
    public int getTotal() {
        return userDAO.getTotal();
    }

    @Override
    public Long getUserChart(String date) {
        return userDAO.getUserChart(date);
    }

//    *************************************
//    MEDAL 메달
//    *************************************

    /* 회원가입시 메달1 획득 */
    @Override
    public void register(UserVO userVO) {
        userDAO.register(userVO);
        achievementDAO.insertMedal(userVO.getUserNumber(), "1");
    }

    @Override
    public List<String> getMedal(Long userNumber) {
        return userDAO.selectAchievementByUserNumber(userNumber);
    }

    @Override
    public void insertMedal(Long userNumber, String medal) {
        achievementDAO.insertMedal(userNumber, medal);
    }

    @Override
    public int medal4Condition(Long userNumber) { return achievementDAO.medal4Condition(userNumber); }

    @Override
    public int medal5Condition(Long userNumber) { return achievementDAO.medal5Condition(userNumber); }

    @Override
    public int medal8Condition(Long userNumber, String category) { return achievementDAO.medal8Condition(userNumber, category); }

    @Override
    public int medalInsertBlock(Long userNumber, String medal) { return achievementDAO.medalInsertBlock(userNumber, medal); }


}
