package edu.neu.cs5200.HW5.orm.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.neu.cs5200.HW5.orm.models.Site;


@Path("/site")
public class SiteDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("cs5200.HW5");
	//此处是这个project的名字，不是MySQL里的！！！
	EntityManager em = factory.createEntityManager();
	
	//CRUD=create,read,update,delete增改删查
	
	//findSiteById
	@GET
	@Path("/{ID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("ID") Integer siteId) {
	return em.find(Site.class, siteId);
	//啥都不用干，find就好，因为没有对数据库进行操作，也不用getTransaction
	}
	
	//findAllSites
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> findAllSites() {
		//select all instance of Site from class Site, not table Site
		//这里是javax的query language，不是sql
		Query query = em.createQuery("select site from Site site");
		return (List<Site>)query.getResultList();
		//query.getResultList()得到的是list of objects,cast成List<Site>，return
	}
	
	//createSite
	//也可以用@GET来做，此处用@POST@Consume,是说利用json的数据进行post操作
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site) {
		List<Site> sites = new ArrayList<Site>();
		
		//开始getTransaction,最后commit这个结果
		em.getTransaction().begin();
		em.persist(site);
		//make it into database
		//此时movie成为managed object，
		//此时其所有的改动都已经和database中的record连接起来 
		
		Query query = em.createQuery("select site from Site site");
		sites = (List<Site>)query.getResultList();
		em.getTransaction().commit(); 
		return sites;
	}	
		
		
	//updateSite
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> updateSite(@PathParam("id") int siteId, Site site) {
		List<Site> sites = new ArrayList<Site>();
		
		em.getTransaction().begin();
		site.setId(siteId);
		//这里把site的id设置为传递进来的ID，因为merge只能以object为参数
		//这个site是新创建的用于传递参数的临时变量
		em.merge(site);
		//合并这里的site和它 correspond to 的database中的site，有不同的，改之
		
		//最后利用query.getResultList来产生结果的List
		Query query = em.createQuery("select site from Site site");
		sites = (List<Site>)query.getResultList();
		em.getTransaction().commit();
		return sites;
	}		
		
	//removeSite
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> removeSite(@PathParam("id") int siteId) {
		List<Site> sites = new ArrayList<Site>();
		
		em.getTransaction().begin();
		Site site = em.find(Site.class, siteId);
//		em.createQuery("UPDATE Site site SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
		//删除完成后设置 vs 把所有有关的都删掉
		//SET FOREIGN_KEY_CHECKS = 1; 
		em.remove(site);
//		em.createQuery("UPDATE Site site SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
		
		Query query = em.createQuery("select site from Site site");
		sites = (List<Site>)query.getResultList();
		em.getTransaction().commit();
		return sites;
	}

	
	
	public static void main(String[] args) {
		
		SiteDAO dao = new SiteDAO();

		//findSiteById
		
//		Site site = dao.findSite(1);
//		System.out.println(site.getName());
		
		//findAllSites
		
//		List<Site> sites = dao.findAllSites();
//		for(Site site : sites) {
//			System.out.println(site.getName());
//		}
		
		//updateSite
		//这里要先新建一个sister site，然后再set想要更改的attribute，
		//这样才会有只更改某个attribute，否则会把剩下的都改为0（因为没数据）
		
//		Site newSite = dao.findSite(1);
//		newSite.setName("NewSite 1");
//		
//		List<Site> sites = dao.updateSite(1,newSite);
//		for(Site site : sites) {
//			System.out.println(site.getName());
//		}
		
		//removeSite
//		dao.removeSite(1);
//		List<Site> sites = dao.findAllSites();
//		for(Site site : sites) {
//			System.out.println(site.getName());
//		}
		
		//createSite
//		Site site = new Site(4, "Site 4", 11.11, 44.44);
//		
//		dao.createSite(site);
//		List<Site> sites = dao.findAllSites();
//		for(Site site1 : sites) {
//			System.out.println(site1.getName());
//		}

		
		
	}
}
