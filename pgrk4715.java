/* Navya Martin Kollapally CS610 PrP*/
/**
 * 
 */



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author navya
 *
 */
public class pgrk4715
{

	/**
	 * @param args
	 */
	private static List<ArrayList<Integer>> adjacencyList=new ArrayList<ArrayList<Integer>>();
	private static int noOfIterations;
	private static int n;
	private static int m;
	private static double factor;
	private static int initialValue;
	private static double []pR;
	private static double []outDegree;
	private static double [] pR0;
	private static final double d=0.85;
    private static double error;
	public static void main(String[] args) throws FileNotFoundException 
	{
		// TODO Auto-generated method stub
		noOfIterations=Integer.parseInt(args[0]);
	    initialValue=Integer.parseInt(args[1]);
	    String Inputfile=args[2];
		
		try 
		{
			extractFile(Inputfile);
			
			
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count=0;
		//print page rank
		outputPageRank(count);
		do
		{
			//calculate page rank
			findPageRank();
			++count;
			if(n<=10)
			{
				outputPageRank(count);
			}
		}while(!isConverging(count));
		if(n>10)
		{
			outputPageRankLargeNode(count);
		}
		
	}
	private static void findPageRank() 
	{
		// TODO Auto-generated method stub
		double sum;
		double []newpR=new double[n];
	
		for(int i=0;i<pR.length;i++)
		{ 
			sum=0.0;
			
			for(int j=0;j<adjacencyList.size();j++)
			{
				if(adjacencyList.get(j).contains(i))
				{
					sum=sum+pR[j]/outDegree[j];
				}
				newpR[i]=factor+d*sum;
			}
			//pR0[i]=pR[i];
		//	pR[i]
	}
		pR0=pR;
		pR=newpR;
		
	}
	private static void outputPageRankLargeNode(int count) 
	{
		// TODO Auto-generated method stub
		System.out.print("Iteration :"+count+" : ");
		DecimalFormat df=new DecimalFormat("0.0000000");
		for(int i=0;i<pR.length;i++)
		{
			System.out.print("PR["+i+"]="+df.format(pR[i])+" ");
		}
		
	}
	private static boolean isConverging(int count) 
	{
		// TODO Auto-generated method stub
		if(count==noOfIterations)
		{
			return true;
		}
		else 
		{
		for(int i=0;i<n;i++)
		{
			if(Math.abs(pR[i]-pR0[i])>error)
				return false;
		}
		return true;
		}
	}
	private static void outputPageRank(int noOfIterations) 
	{
		// TODO Auto-generated method stub
		if(noOfIterations==0)
		{
			System.out.print("Base :"+noOfIterations +":");
		}
		else
		{
			System.out.print("Iteration :"+noOfIterations +":");
		}
		DecimalFormat df=new DecimalFormat("0.0000000");
		for(int i=0;i<pR.length;i++)
		{
			System.out.print("PR["+i+"]="+df.format(pR[i])+" ");
		}
		System.out.println();
		
	}
	private static void extractFile(String inputfile) throws IOException 
	{
		// TODO Auto-generated method stub
		FileInputStream input=new FileInputStream(inputfile);
		BufferedReader brfile=new BufferedReader(new InputStreamReader(input));
		String inputline;
		String [] infile;
		outDegree=new double[n];
		int flag=0;
		while(brfile.ready())
		{
			inputline=brfile.readLine();
			
			if(flag==0)
			{
				for(int i=0;i<1;i++)
				{
				infile=inputline.split(" ");
				n=Integer.parseInt(infile[i]);
				m=Integer.parseInt(infile[i++]);
				if(n>10)
				{
					noOfIterations=0;
					initialValue=-1;
					
				}
				initialiseValues(initialValue);
				error=calculateErrorRate();
				}
			}
			if(flag!=0)
			{
			infile=inputline.split(" ");
			int v1=Integer.parseInt(infile[0]);
			int v2=Integer.parseInt(infile[1]);
			
			adjacencyList.get(v1).add(v2);
			
		   }
			flag=2;
		}
			for(int i=0;i<n;i++)
			{
				outDegree[i]=adjacencyList.get(i).size();
			}
			
			brfile.close();
		}
		
	
	private static double calculateErrorRate() 
	{
		// TODO Auto-generated method stub
		if(noOfIterations>=0)
		{
			return 0.00001;
		}
		
		else if(noOfIterations<0)
				{
					error=Math.pow(10, noOfIterations);
				}
		
		
		return error;
		
	}
	private static void initialiseValues(int initial) 
	{
		// TODO Auto-generated method stub
		double initialise;
		adjacencyList=new ArrayList<ArrayList<Integer>>(n);
		outDegree=new double[n];
		pR0=new double[n];
		pR=new double[n];
		
		factor=(1-d)/n;
		switch(initial)
		{
		case -2:
			initialise=1/Math.sqrt(n);
			break;
		case -1:initialise=1/(double)n;
			break;
		case 0:initialise=0;
			break;
		case 1:initialise=initial;
			break;
		default:
			initialise=initial;
			break;
			
		}
		for(int i=0;i<n;i++)
		{
			adjacencyList.add(new ArrayList<>());
			pR[i]=initialise;
			pR0[i]=initialise;
		}
		
		
	}

}

