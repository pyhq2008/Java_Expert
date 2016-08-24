package com.baoxue.database.mongodb;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.bson.types.ObjectId;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/24.
 */
public class MongoDB4CRUDTest {

    private Mongo mg = null;
    private DB db;
    private DBCollection users;


    public void init() {
        try {
            mg = new Mongo();
            //mg = new Mongo("localhost", 27017);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //��ȡtemp DB�����Ĭ��û�д�����mongodb���Զ�����
        db = mg.getDB("temp");
        //��ȡusers DBCollection�����Ĭ��û�д�����mongodb���Զ�����
        users = db.getCollection("users");
    }

    public void destory() {
        if (mg != null)
            mg.close();
        mg = null;
        db = null;
        users = null;
        System.gc();
    }

    public void print(Object o) {
        System.out.println(o);
    }

    private void queryAll() {
        print("��ѯusers���������ݣ�");
        //db�α�
        DBCursor cur = users.find();
        while (cur.hasNext()) {
            print(cur.next());
        }
    }

    public void add() {
        //�Ȳ�ѯ��������
        queryAll();
        print("count: " + users.count());

        DBObject user = new BasicDBObject();
        user.put("name", "hoojo");
        user.put("age", 24);
        //users.save(user)���棬getN()��ȡӰ������
        //print(users.save(user).getN());

        //��չ�ֶΣ����������ֶΣ���Ӱ����������
        user.put("sex", "��");
        print(users.save(user).getN());

        //���Ӷ������ݣ�����Array����
        print(users.insert(user, new BasicDBObject("name", "tom")).getN());

        //����List����
        List<DBObject> list = new ArrayList<DBObject>();
        list.add(user);
        DBObject user2 = new BasicDBObject("name", "lucy");
        user.put("age", 22);
        list.add(user2);
        //����List����
        print(users.insert(list).getN());

        //��ѯ�����ݣ������Ƿ����ӳɹ�
        print("count: " + users.count());
        queryAll();
    }

    public void remove() {
        queryAll();
        print("ɾ��id = 4de73f7acd812d61b4626a77��" + users.remove(new BasicDBObject("_id", new ObjectId("4de73f7acd812d61b4626a77"))).getN());
        print("remove age >= 24: " + users.remove(new BasicDBObject("age", new BasicDBObject("$gte", 24))).getN());
    }


    public void modify() {
        print("�޸ģ�" + users.update(new BasicDBObject("_id", new ObjectId("4dde25d06be7c53ffbd70906")), new BasicDBObject("age", 99)).getN());
        print("�޸ģ�" + users.update(
                new BasicDBObject("_id", "4dde2b06feb038463ff09042"),
                new BasicDBObject("age", 121),
                true,//������ݿⲻ���ڣ��Ƿ�����
                false//�����޸�
        ).getN());
        print("�޸ģ�" + users.update(
                new BasicDBObject("name", "haha"),
                new BasicDBObject("name", "dingding"),
                true,//������ݿⲻ���ڣ��Ƿ�����
                true//falseֻ�޸ĵ�һ�죬true����ж����Ͳ��޸�
        ).getN());

        //�����ݿⲻ���ھͲ��޸ġ����������ݣ����������ݾͲ��޸�
        //print("�޸Ķ�����" + coll.updateMulti(new BasicDBObject("_id", new ObjectId("4dde23616be7c19df07db42c")), new BasicDBObject("name", "199")));
    }


    public void query() {
        //��ѯ����
        //queryAll();

        //��ѯid = 4de73f7acd812d61b4626a77
        print("find id = 4de73f7acd812d61b4626a77: " + users.find(new BasicDBObject("_id", new ObjectId("4de73f7acd812d61b4626a77"))).toArray());

        //��ѯage = 24
        print("find age = 24: " + users.find(new BasicDBObject("age", 24)).toArray());

        //��ѯage >= 24
        print("find age >= 24: " + users.find(new BasicDBObject("age", new BasicDBObject("$gte", 24))).toArray());
        print("find age <= 24: " + users.find(new BasicDBObject("age", new BasicDBObject("$lte", 24))).toArray());

        print("��ѯage!=25��" + users.find(new BasicDBObject("age", new BasicDBObject("$ne", 25))).toArray());
        print("��ѯage in 25/26/27��" + users.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.IN, new int[] { 25, 26, 27 }))).toArray());
        print("��ѯage not in 25/26/27��" + users.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.NIN, new int[] { 25, 26, 27 }))).toArray());
        print("��ѯage exists ����" + users.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.EXISTS, true))).toArray());

        print("ֻ��ѯage���ԣ�" + users.find(null, new BasicDBObject("age", true)).toArray());
        print("ֻ�����ԣ�" + users.find(null, new BasicDBObject("age", true), 0, 2).toArray());
        print("ֻ�����ԣ�" + users.find(null, new BasicDBObject("age", true), 0, 2, Bytes.QUERYOPTION_NOTIMEOUT).toArray());

        //ֻ��ѯһ�����ݣ�����ȥ��һ��
        print("findOne: " + users.findOne());
        print("findOne: " + users.findOne(new BasicDBObject("age", 26)));
        print("findOne: " + users.findOne(new BasicDBObject("age", 26), new BasicDBObject("name", true)));

        //��ѯ�޸ġ�ɾ��
        print("findAndRemove ��ѯage=25�����ݣ�����ɾ��: " + users.findAndRemove(new BasicDBObject("age", 25)));

        //��ѯage=26�����ݣ������޸�name��ֵΪAbc
        print("findAndModify: " + users.findAndModify(new BasicDBObject("age", 26), new BasicDBObject("name", "Abc")));
        print("findAndModify: " + users.findAndModify(
                new BasicDBObject("age", 28), //��ѯage=28������
                new BasicDBObject("name", true), //��ѯname����
                new BasicDBObject("age", true), //����age����
                false, //�Ƿ�ɾ����true��ʾɾ��
                new BasicDBObject("name", "Abc"), //�޸ĵ�ֵ����name�޸ĳ�Abc
                true,
                true));

        queryAll();
    }

    public void testOthers() {
        DBObject user = new BasicDBObject();
        user.put("name", "hoojo");
        user.put("age", 24);

        //JSON ����ת��
        print("serialize: " + JSON.serialize(user));
        //�����л�
        print("parse: " + JSON.parse("{ \"name\" : \"hoojo\" , \"age\" : 24}"));

        print("�ж�temp Collection�Ƿ����: " + db.collectionExists("temp"));

        //��������ھʹ���
        if (!db.collectionExists("temp")) {
            DBObject options = new BasicDBObject();
            options.put("size", 20);
            options.put("capped", 20);
            options.put("max", 20);
            print(db.createCollection("account", options));
        }


        //ֻ������д������
        db.getCollection("test").save(user);
    }

}