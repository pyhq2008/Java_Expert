package com.tingyun.cassandra;

/**
 * Created by Administrator on 2015/6/24.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class TestCassandra {

    private Cluster cluster;

    private Session session;

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * ���ӽڵ�
     *
     * @param node
     */
    public void connect(String node) {
        cluster = Cluster.builder().addContactPoint(node).build();
        Metadata metadata = cluster.getMetadata();
        System.out.printf("Connected to cluster: %s\n",
                metadata.getClusterName());
        for (Host host : metadata.getAllHosts()) {
            System.out.printf("Datatacenter: %s; Host: %s; Rack: %s\n",
                    host.getDatacenter(), host.getAddress(), host.getRack());
        }

        this.session = cluster.connect();
    }

    public void creatCF(){
        String cqlStatement = "CREATE TABLE  users  ( " +
               " id varchar PRIMARY KEY," + " first_name varchar ," + " last_name varchar ,"
                + " file blob" + ");";
    }


    public void selectData(){
        ResultSet resultSet = getSession().execute(
                "SELECT * FROM pimin_net.filetable13;");

        for (Row row : resultSet) {
            System.out.println(row.toString());


        }
    }

    private void insert() {


        String cqlStatement = "INSERT INTO pimin_net.users"
                + " ( filename, password) VALUES ('gt', '123456')";
        session.execute(cqlStatement);
    }

    public void insertData() {
        PreparedStatement insertStatement = getSession().prepare(
                "INSERT INTO pimin_net.users "
                        + "(id, first_name, last_name, age, emails,avatar) "
                        + "VALUES (?, ?, ?, ?, ?, ?);");

        BoundStatement boundStatement = new BoundStatement(insertStatement);
        Set<String> emails = new HashSet<String>();
        emails.add("xxx@qq.com");
        emails.add("xxx@163.com");

        java.nio.ByteBuffer avatar = null;
        try {
            avatar = toByteBuffer("f:\\user.png");
            avatar.flip();
            System.out.println("ͷ���С��" + avatar.capacity());
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

        getSession()
                .execute(
                        boundStatement.bind(
                                UUID.fromString("756716f7-2e54-4715-9f00-91dcbea6cf50"),
                                "pi", "min", 10, emails, avatar));

    }

    public void loadData() {
        ResultSet resultSet = getSession().execute(
                "SELECT first_name,last_name,age,avatar FROM pimin_net.users;");
        System.out
                .println(String
                        .format("%-30s\t%-20s\t%-20s\n%s", "first_name",
                                "last_name", "age",
                                "-------------------------------+-----------------------+--------------------"));
        for (Row row : resultSet) {
            System.out.println(String.format("%-30s\t%-20s\t%-20s",
                    row.getString("first_name"), row.getString("last_name"),
                    row.getInt("age")));

            ByteBuffer byteBuffer = row.getBytes("avatar");
            System.out.println("ͷ���С��"
                    + (byteBuffer.limit() - byteBuffer.position()));

            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream("f:\\2.png");
            } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                fileOutputStream.write(byteBuffer.array(),
                        byteBuffer.position(),
                        byteBuffer.limit() - byteBuffer.position());
                fileOutputStream.close();
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        System.out.println();

    }

    public void close() {
        cluster.close();
    }

    /**
     * ��ȡ�ļ�
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static ByteBuffer toByteBuffer(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
// do nothing
// System.out.println("reading");
            }
            return byteBuffer;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestCassandra client = new TestCassandra();
        client.connect("127.0.0.1");
        client.insert();
//        client.loadData();
//        client.session.close();
        client.close();
    }
}