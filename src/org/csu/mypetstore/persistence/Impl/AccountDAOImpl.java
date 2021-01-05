package org.csu.mypetstore.persistence.Impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAOImpl implements AccountDAO {
    private static final String GETACCOUNTBYUSERNAME = "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY,ACCOUNT.STATE,ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE, PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String GETACCOUNTBYUSERNAMEANDPASSWORD = "SELECT SIGNON.USERNAME,ACCOUNT.EMAIL,ACCOUNT.FIRSTNAME,ACCOUNT.LASTNAME,ACCOUNT.STATUS,ACCOUNT.ADDR1 AS address1,ACCOUNT.ADDR2 AS address2,ACCOUNT.CITY,ACCOUNT.STATE, ACCOUNT.ZIP,ACCOUNT.COUNTRY,ACCOUNT.PHONE,PROFILE.LANGPREF AS languagePreference,PROFILE.FAVCATEGORY AS favouriteCategoryId,PROFILE.MYLISTOPT AS listOption,PROFILE.BANNEROPT AS bannerOption,BANNERDATA.BANNERNAME FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA WHERE ACCOUNT.USERID = ? AND SIGNON.PASSWORD = ? AND SIGNON.USERNAME = ACCOUNT.USERID AND PROFILE.USERID = ACCOUNT.USERID AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static final String UPDATEACCOUNT = "UPDATE ACCOUNT SET EMAIL = ?,FIRSTNAME = ?,LASTNAME = ?,STATUS = ?,ADDR1 = ?," +
            "ADDR2 = ?,CITY = ?,STATE = ?,ZIP = ?,OUNTRY = ?,PHONE = ?WHERE USERID = ?";
    private static final String INSERTACCOUNT = "INSERT INTO ACCOUNT (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, " +
            "CITY, STATE, ZIP, COUNTRY, PHONE, USERID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATAPROFILE = " UPDATE PROFILE SET LANGPREF = ?,FAVCATEGORY = ? WHERE USERID = ?";
    private static final String INSERTPROFILE = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID) VALUES (?, ?, ?)";
    private static final String UPDATASIGNON = "UPDATE SIGNON SET PASSWORD = ? WHERE USERNAME = ?";
    private static final String INSERTSIGNON = "INSERT INTO SIGNON (USERNAME,PASSWORD) VALUES (?, ?)";


    //获取账户
    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GETACCOUNTBYUSERNAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                account = new Account();
                getResultSetIntoAccount(resultSet,account);
            }
            DBUtil.closePreparedStatementETC(connection,preparedStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    //获取账户通过账号密码
    @Override
    public Account getAccountByUsernameAndPassword(Account account) {
        Account myaccount = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GETACCOUNTBYUSERNAMEANDPASSWORD);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                myaccount = new Account();
                getResultSetIntoAccount(resultSet,myaccount);
            }
            DBUtil.closePreparedStatementETC(connection,preparedStatement,resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return myaccount;
    }

    //插入账户
    @Override
    public void insertAccount(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTACCOUNT);
            setPreparestatementByAccount(preparedStatement,account);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatementETC(connection,preparedStatement,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertProfile(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTPROFILE);
            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setString(3,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatementETC(connection,preparedStatement,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTSIGNON);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatementETC(connection,preparedStatement,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATEACCOUNT);
            setPreparestatementByAccount(preparedStatement,account);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatementETC(connection,preparedStatement,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateProfile(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATAPROFILE);
            preparedStatement.setString(1,account.getLanguagePreference());
            preparedStatement.setString(2,account.getFavouriteCategoryId());
            preparedStatement.setString(3,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatementETC(connection,preparedStatement,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSignon(Account account) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATASIGNON);
            preparedStatement.setString(1,account.getPassword());
            preparedStatement.setString(2,account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatementETC(connection,preparedStatement,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getResultSetIntoAccount(ResultSet resultSet , Account account) throws Exception{
        account.setUsername(resultSet.getString(1));
        account.setEmail(resultSet.getString(2));
        account.setFirstName(resultSet.getString(3));
        account.setLastName(resultSet.getString(4));
        account.setStatus(resultSet.getString(5));
        account.setAddress1(resultSet.getString(6));
        account.setAddress2(resultSet.getString(7));
        account.setCity(resultSet.getString(8));
        account.setState(resultSet.getString(9));
        account.setZip(resultSet.getString(10));
        account.setCountry(resultSet.getString(11));
        account.setPhone(resultSet.getString(12));
        account.setLanguagePreference(resultSet.getString(13));
        account.setFavouriteCategoryId(resultSet.getString(14));
        account.setListOption(resultSet.getBoolean(15));
        account.setBannerOption(resultSet.getBoolean(16));
    }

    private void setPreparestatementByAccount(PreparedStatement preparedStatement , Account account)throws Exception{
        preparedStatement.setString(1,account.getEmail());
        preparedStatement.setString(2,account.getFirstName());
        preparedStatement.setString(3,account.getLastName());
        preparedStatement.setString(4,account.getStatus());
        preparedStatement.setString(5,account.getAddress1());
        preparedStatement.setString(6,account.getAddress2());
        preparedStatement.setString(7,account.getCity());
        preparedStatement.setString(8,account.getState());
        preparedStatement.setString(9,account.getZip());
        preparedStatement.setString(10,account.getCountry());
        preparedStatement.setString(11,account.getPhone());
        preparedStatement.setString(12,account.getUsername());
    }
}
