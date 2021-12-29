package com.ruiya;

import com.ruiya.bean.FundBalanceDTO;
import com.ruiya.bean.Pet;
import com.ruiya.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootTest
 class MongoTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void testZlRecommendColumnElse(){
        User user = new User();
        Query query = Query.query(Criteria.where("name").is("liusi"));
//		long count = mongoTemplate.count(query, "zl_recommend_column");
        System.out.println("count112"+ mongoTemplate.find(query,User.class));
        System.out.println("count1123"+ mongoTemplate.find(query,User.class,"user"));
        System.out.println("count1124"+ mongoTemplate.findAll(User.class));
        System.out.println("count1125"+ mongoTemplate.findAll(User.class,"user"));
        System.out.println("count1126"+ mongoTemplate.query(User.class).stream().count());
        long count1 = mongoTemplate.count(query, User.class, "user");
        System.out.println("count1:"+count1);
        log.info("--------------------->[MongoDB testMongoTemplateElse end]");
//		Assertions.assertSame(1, count1);
    }

    @Test
    void testMongoTemplateElse(){
        Query query = Query.query(Criteria.where("name").is("lisi"));
        long count1 = mongoTemplate.count(query, User.class);
//		long count1 = mongoTemplate.count(query, "zlRecommendColumnInfo");
        System.out.println("count1:"+count1);
        query = Query.query(Criteria.where("age").is(1966598847));
        mongoTemplate.remove(query,  User.class);

//        mongoTemplate.remove(query, User.class);
        query = Query.query(Criteria.where("name").is("lisi"));
        long count = mongoTemplate.count(query, User.class);
        System.out.println("count:"+count);
        log.info("--------------------->[MongoDB testMongoTemplateElse end]");
//		Assertions.assertSame(1, count1);
    }

    @Test
    void testFindAllElse() {
        log.info("--------------------->[MongoDB find start]");
        List<User> all = mongoTemplate.findAll(User.class);
        for (User user : all) {
            System.out.println(user.getName());
        }
        log.info("--------------------->[MongoDB find end]");
//		Assertions.assertSame(1, all.size());
    }


    @Test
    void saveObjElse() {
        User user  = new User();
        log.info("--------------------->[MongoDB save start]");
        user.setName("lisi121");
        user.setAge(new Random().nextInt());
        user.setPet(new Pet("dog"));
        mongoTemplate.save(user);
        log.info("添加成功");
        Assertions.assertNotNull(user);
        Query query = new Query(Criteria.where("name").is("lisi"));
        List<User> userList = mongoTemplate.find(query, User.class);
        System.out.println("userList = " + userList);
    }


    @Test
     public void get(){
        // 1001263008         1001883566
         queryCustFundBalance("1001883566");
     }


    public List<FundBalanceDTO> queryCustFundBalance(String custNo){

        List<FundBalanceDTO> fundBalanceDTOList = new ArrayList<>();
//        Pattern pattern = Pattern.compile("^((?!"+"ZYB"+").)*$", Pattern.CASE_INSENSITIVE);

        //封装查询条件
        List<AggregationOperation> operations = new ArrayList<>();
//        operations.add(Aggregation.group("fundid").sum("balance").as("balance").sum("mktvalue").as("mktvalue"));
//        operations.add(Aggregation.match(Criteria.where("custno").is(custNo).and("mktvalue").gt(0).and("fundid").regex(pattern)));

        operations.add(Aggregation.match(Criteria.where("custno").is(custNo)));
        operations.add(Aggregation.group("fundid").sum("balance").as("balance"));

        Aggregation aggregation = Aggregation.newAggregation(operations);
        AggregationResults<FundBalanceDTO> fundbalance = mongoTemplate.aggregate(aggregation, "fundbalance", FundBalanceDTO.class);

        System.out.println("fundbalance = " + fundbalance.getMappedResults().size());
        for (int i = 0; i < fundbalance.getMappedResults().size(); i++) {
            fundBalanceDTOList.add(fundbalance.getMappedResults().get(i));
        }

        return fundBalanceDTOList;
    }
}
