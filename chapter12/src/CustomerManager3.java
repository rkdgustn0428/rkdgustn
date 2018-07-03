import java.util.ArrayList;
import java.util.Scanner;

public class CustomerManager3 {

	//고객 정보를 저장할 자료구조 선언
	static ArrayList<Customer> custList = new ArrayList<>();

	//리스트 정보를 조회하기 위해 인덱스를 필요로 함
	static int index = 0;

	static int count = 0;//custList.size()

	//기본 입력장치로부터 데이터를 입력받기 위해 Scanner객체 생성
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		while(true) {
			count = custList.size();
			System.out.printf("\n[INFO] 고객 수 : %d, 인덱스 : %d\n", count, index);
			System.out.println("메뉴를 입력하세요.");
			System.out.println("(I)nsert, (P)revious, (N)ext, " +
					"(C)urrent, (U)pdate, (D)elete, (Q)uit, (S)tatistics, (G)etname, (T)eleport");
			System.out.print("메뉴 입력: ");
			String menu = scan.next();
			menu = menu.toLowerCase();	//입력한 문자열을 모두소문자로 변환
			switch(menu.charAt(0)) {
			case 'i':
				System.out.println("고객정보 입력을 시작합니다.");
				insertCustomerData();
				System.out.println("고객정보를 입력했습니다.");
				break;
			case 'p' :
				System.out.println("이전 데이터를 출력합니다.");
				if(index <= 0) {
					System.out.println("이전 데이터가 존재하지 않습니다.");
				}else {
					index--;
					printCustomerData(index);
				}
				break;
			case 'n' :
				System.out.println("다음 데이터를 출력합니다.");
				if(index >= count-1) {
					System.out.println("다음 데이터가 존재하지 않습니다.");
				}else {
					index++;
					printCustomerData(index);
				}
				break;
			case 'c' :
				System.out.println("현재 데이터를 출력합니다.");
				if( (index >= 0) && (index < count)) {
					printCustomerData(index);
				}else {
					System.out.println("출력할 데이터가 선택되지 않았습니다.");
				}
				break;			
			case 'u' :
				System.out.println("데이터를 수정합니다.");
				if( (index >= 0) && (index < count)) {
					System.out.println(index + "번째 데이터를 수정합니다.");
					updateCustomerData(index);
				}else {
					System.out.println("수정할 데이터가 선택되지 않았습니다.");
				}
				break;
			case 'd' :
				System.out.println("데이터를 삭제합니다.");
				if( (index >= 0) && (index < count)) {
					System.out.println(index + "번째 데이터를 삭제합니다.");
					deleteCustomerData(index);
				}else {
					System.out.println("삭제할 데이터가 선택되지 않았습니다.");
				}
				break;
			case 'q' :
				System.out.println("프로그램을 종료합니다.");
				scan.close();	//Scanner 객체를 닫아준다.
				System.exit(0);	//프로그램을 종료시킨다.
				break;	
			case 's' :
				if(count==0) {
					System.out.println("고객이 없습니다.");
				}else {
					System.out.println("나이대별 성비를 출력합니다.");
					printStatic();
				}
				break;
			case 'g':
				getSearch();
				break;
			case 't':
				
				tele();
				break;
			default : 
				System.out.println("메뉴를 잘 못 입력했습니다.");	
			}//end switch
		}//end while
	}//end main
	
	
	public static void getSearch() {
		String name;
		String name2;
		int size;
		System.out.print("찾을 이름을 입력하세요 :");
		name = scan.next();
		size = name.length();
		Customer cust;
		
		for(int i=0;i<count;i++) {
			cust = custList.get(i);
			name2 = cust.getName().substring(0, size);
			if(name2.equals(name))
				System.out.println("이름 : "+ cust.getName() + "   /   index : " + i);
		}
		
	}
	public static void insertCustomerData() {
		System.out.print("이름 : ");	
		String name = scan.next();
		char gender;
		while(true)
		{
		System.out.print("성별(M/F) : ");	
		gender = scan.next().charAt(0);
		if(gender == 'm' || gender == 'M' || gender == 'f' || gender == 'F') 
			break;
		else
			System.out.println("잘못된 값입니다. 다시 입력해주세요.");
		}
		System.out.print("이메일 : ");	
		String email = scan.next();
		System.out.print("출생년도 : ");	
		int birthYear = scan.nextInt();

		//입력받은 데이터로 고객 객체를 생성
		Customer cust = new Customer(name, gender, email, birthYear);

		//고객 객체를 ArrayList에 저장
		custList.add(cust);
	}
	
	public static void tele() {
		int location;
		while(true){
			System.out.print("고객 번호를 입력하십시오. : ");
			location=scan.nextInt();
			if(0<= location && location < count) {
				
				break;
			}
			System.out.println("고객 번호 오류");
		}
		
		System.out.println("---------------");
		index = location;
		printCustomerData(index);
		
		
	}
	
	public static void printStatic() {
		System.out.println("===========성비===========");
		int a1=0,a2=0,a3=0,a4=0,a5=0; // 연대별
		int b1m=0,b1f=0,b2m=0,b2f=0,b3m=0,b3f=0,b4m=0,b4f=0,b5m=0,b5f=0; //성비
		Customer cust;
		for(int i=0;i<=count-1;i++) {
			cust = custList.get(i);
			if(cust.getBirthYear()>=2000) {
				a1++;
				if(cust.getGender()=='m' || cust.getGender()=='M') 
					b1m++;
				else
					b1f++;
			}
			else if(cust.getBirthYear()>=1990) {
				a2++;
				if(cust.getGender()=='m' || cust.getGender()=='M') 
					b2m++;
				else
					b2f++;				
			}
			else if(cust.getBirthYear()>=1980) {
				a3++;
				if(cust.getGender()=='m' || cust.getGender()=='M') 
					b3m++;
				else
					b3f++;				
			}
			else if(cust.getBirthYear()>=1970) {
				a4++;
				if(cust.getGender()=='m' || cust.getGender()=='M') 
					b4m++;
				else
					b4f++;				
			}
			else {
				a5++;
				if(cust.getGender()=='m' || cust.getGender()=='M') 
					b5m++;
				else
					b5f++;				
			}
		}
		
		System.out.println("2000s : 총 : " + a1 + " 명, 남자 : " + b1m + "명, 여자 : "+ b1f +"명");
		System.out.println("1990s : 총 : " + a2 + " 명, 남자 : "+ b2m + "명, 여자 : "+ b2f +"명");
		System.out.println("1980s : 총 : " + a3 + " 명, 남자 : "+ b3m + "명, 여자 : "+ b3f +"명");
		System.out.println("1970s : 총 : " + a4 + " 명, 남자 : "+ b4m + "명, 여자 : "+ b4f +"명");
		System.out.println("~1960s : 총 : " + a5 + " 명, 남자 : "+ b5m + "명, 여자 : "+ b5f +"명");
	}

	//고객데이터 출력
	public static void printCustomerData(int index) {
		Customer cust = custList.get(index);
		System.out.println("==========CUSTOMER INFO================");
		System.out.println("이름 : " + cust.getName());
		System.out.println("성별 : " + cust.getGender());
		System.out.println("이메일 : " + cust.getEmail());
		System.out.println("출생년도 : " + cust.getBirthYear());
		System.out.println("=======================================");
	}

	//index 위치의 고객정보를 삭제합니다.
	public static void deleteCustomerData(int index) {
		custList.remove(index);
	}

	//index 위치의 고객 정보를 수정합니다.
	public static void updateCustomerData(int index) {
		Customer cust = custList.get(index);
		System.out.println("---------UPDATE CUSTOMER INFO----------");
		System.out.print("이름(" + cust.getName() + ") :");
		cust.setName(scan.next());

		System.out.print("성별(" + cust.getGender() + ") :");
		cust.setGender(scan.next().charAt(0));

		System.out.print("이메일(" + cust.getEmail() + ") :");
		cust.setEmail(scan.next());

		System.out.print("출생년도(" + cust.getBirthYear() + ") :");
		cust.setBirthYear(scan.nextInt());		
	}

}//end class