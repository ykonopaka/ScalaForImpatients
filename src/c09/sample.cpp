#include <iostream.h>
#include <conio.h>
#include <string.h>

void main()
{
clrscr();
int slength;
char x[81]; //Allowing the user to input a maximum of 80 characters.
cout << "Enter the \"string\" : " << endl;
cin>>x;
slength=strlen(x);
cout << "The length \\ of the string" << x << " is " << slength << "." << endl;
getch();
}