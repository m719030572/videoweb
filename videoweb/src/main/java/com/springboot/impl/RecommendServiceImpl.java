package com.springboot.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.springboot.domain.Recommend;
import com.springboot.domain.Video;
import com.springboot.repository.RecommendRepository;
import com.springboot.repository.VideoRepository;
import com.springboot.service.RecommendService;
import com.springboot.service.VideoService;
@Service
@Component
public class RecommendServiceImpl implements RecommendService
{
	@Autowired
	RecommendRepository rr;
	@Autowired
	VideoService vs;
	@Autowired
	VideoRepository vr;

	@Override
	public List<Integer> getListAsUid(String uid) {
		List<Integer> videolist=new ArrayList<>();
		
		Map<Integer,Integer> datamap=new HashMap<>();
		String inputstr="G:\\result\\"+uid+".csv";
		InputStream list;
		try {
			list = new FileInputStream(inputstr);

		Scanner scanner = new Scanner(list);
		while (scanner.hasNextLine()) 
		{
			String str=scanner.nextLine();
			List<String> strs=new ArrayList<String>(Arrays.asList(str.split(",")));
			datamap.put(Integer.parseInt(strs.get(0)),Integer.parseInt(strs.get(1)));
		}
		scanner.close();
		list.close();
		//选择10个视频
		int tmp_max=0;
		int tmp_max_key=0;
		int last_max=5000;
		for(int i=0;i<10;i++)
		{
			tmp_max=0;
			for(int key:datamap.keySet())
			{
				int tmp_int=datamap.get(key);
				if(tmp_int>tmp_max&&tmp_int<last_max)
				{
					tmp_max=tmp_int;
					tmp_max_key=key;
				}
			}
			videolist.add(tmp_max_key);
			last_max=tmp_max;
		}
	} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return videolist;

	}
	@Override
	public List<Integer> getDefaultRecommendList() {
		List<Recommend> recommendlist=new ArrayList<>();
		List<Integer> videolistint=new ArrayList<>();
		recommendlist=rr.findByUid("default");
		for(Recommend recommend:recommendlist)
		{
			videolistint.add(Integer.parseInt(recommend.getVid()));
		}
		return videolistint;
	}
	@Override
	public void buildRecommendList() {
		try {
			 List<Integer> user_list=new ArrayList<>();
			InputStream list = new FileInputStream("G:\\userlist.txt");
			Scanner scanner = new Scanner(list);
			int user;
			while (scanner.hasNextInt()) 
			{
				user=scanner.nextInt();	//读取用户列表
				user_list.add(user);
			}
			scanner.close();
			list.close();
			list = new FileInputStream("G:\\small_user_out.csv");
			scanner = new Scanner(list);
			//装每个用户看过的视频
			Map<Integer,List<Integer>> videolistlist =new HashMap<Integer,List<Integer>>();
			for (int s:user_list)
			{
				List<Integer> intlist=new ArrayList<>();
				videolistlist.put(s, intlist);
			}
			List<List<Integer>> datalistlist=new ArrayList<>();
			while (scanner.hasNextLine())
			{
				List<Integer> datalist=new ArrayList<>();
				String str=scanner.nextLine();
				List<String> strs=new ArrayList<String>(Arrays.asList(str.split(",")));
				for(String string:strs)
				{
					datalist.add(Integer.parseInt(string));
				}
				datalistlist.add(datalist);
				videolistlist.get(datalist.get(0)).add(datalist.get(1));
			}
			scanner.close();
			list.close();
			list = new FileInputStream("G:\\userout.csv");
			scanner = new Scanner(list);
			int nowuser=0;
			int i=0;
			double tmp_max=0.0;
			double tmp_double;
			int objectuser = 0;
			int tmp_max_location=0;
			double last_max=2.0;
			double[][] userrela=new double[300][11];
			long [][] userrelauser=new long [300][11];
			long [][] recommentlist=new long [300][20];
			while(scanner.hasNextLine())
			{
				String str=scanner.nextLine();
				List<String> strs=new ArrayList<String>(Arrays.asList(str.split(",")));
				last_max=2.0;
				Map<Integer,Integer> videomap =new HashMap<Integer,Integer>();
				for(i=-1;i<10;i++)
				{
					tmp_max=0.0;//当前最大值
					objectuser=0;//当前的目标用户
					tmp_max_location=0;//最大值所在用户数组未知
					for(String string:strs)
					{
						if(string.equals("0"));//先对0的数据进行过滤
						else//选择排序
						{
							tmp_double=Double.parseDouble(string);
							if(tmp_double>tmp_max&&tmp_double<last_max)//比较
							{
								tmp_max=tmp_double;//如果这个是当前最大值，则把他记录下来，继续找
								tmp_max_location=objectuser;
							}
						}
						objectuser++;
					}
					last_max=tmp_max;//上个最大值，用这个做法避免删除数据
					if(i==-1)//去掉自己
						continue;
					userrela[nowuser][i]=tmp_max;//记录下选择的最大值
					userrelauser[nowuser][i]=user_list.get(tmp_max_location);
					
					
					for( int video:(videolistlist.get(user_list.get(nowuser))))
					{
						if(!videomap.containsKey(video))
						{
							videomap.put(video, 11-i);
						}
						else
						{
							videomap.put(video, videomap.get(video)+11-i);
						}
					}
				}
				
				//这里就完成了一个用户的推荐列表
				
				String filestr="G:\\result\\"+user_list.get(nowuser)+".csv";
				File files=new File(filestr);
				if(!files.exists())
				files.createNewFile();
				Writer fos = new FileWriter(filestr);
	            BufferedWriter out = new BufferedWriter(new FileWriter("bwriter"));
				for (int ele  : videomap.keySet())
				{
					String outstr=ele+","+videomap.get(ele)+"\n";
					fos.write(outstr);
					fos.flush();
				}
				fos.close();
				
				nowuser++;
			}
			scanner.close();
			list.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ;

	}
}
