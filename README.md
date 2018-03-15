# AnimalManagement
Name: Kate Kelly 
Student No: 20071666
Course: BSc in Software Systems Development, year 3 
Module: GUI Development
Lecturer: David Drohan  
Functionality:
This animal management system will be used in an animal boarding kennels scenario and allow the user to login or register in the application. 
The application also exhibits full CRUD functionality 
One the user is logged in, they can add details, such as, the Name and contact number of the animal's owner. They can also add the pet's details, with fields such as, pet name, gender, age.

Once an animal is added to the system, the user can view these animal's details, as well as update a particular record within the database. Finally, the user has the option to remove a record from the database altogether 
When the user has completed their desired task(s) they can then logout of the application. At which time, they will be brought back to the login/register screen.
Navigation within the application is achieved using visual icons. 

Persistence:
In order to allow the data to exist after the duration of the application, I implemented Shared Preferences feature, which was used to keep the user logged in within the application.
I then implemented an SQLite database within the application. This was used to write all the records in the system to. This approach coupled with the use of Shared Preferences allow the data entered to be accessed after the application has ended.
All elements of the application are persistent using either Shared Preferences or the SQLite database

UX/DX Approach:
 
Throughout the course of this assignment, every effort was made to ensure that the application facilitates a pleasant experience for the end user. I strived to achieve this by endeavouring to adhere to the recommended User Interface guidelines. I have included conditional application navigation using icons, this will allow the user to easily identify the desired location within the application quickly and effectively and make for overall ease of use. Data validation was included to ensure the user is unable to add an empty record to the database, this will lead to both enhanced user and developer experience. The use of packages which groups similar classes together, aims to enhance the developer experience by make code easier to read and refactor.
 
By adhering to Android best practices, I aim to make this application pleasant to examine and utilise from an android developer perspective. I was conscious of the Android material development guidelines, in relation to things such as, selecting the most appropriate layouts, components and colour scheme. 

This will reduce the overall workload on the end user, which will ultimately result in increased user satisfaction within the application. The use of widgets will enhance the user experience. Using widgets eg Actual Number Pickers instead of a standard edit text field will decrease the likelihood of inaccurate input

References:
the code used in the ClickInterface.java file was adapted from:
https://antonioleiva.com/recyclerview-listener 

code to pass extra fragments between activities(get int extra) was adapted from: https://stackoverflow.com/questions/2137514/getintextra-always-returns-the-default-value  

Number picker: https://github.com/milosmns/actual-number-picker 
 
 Trim(): https://developer.android.com/reference/java/lang/String.html 
 
OnValueChangedListener():
https://stackoverflow.com/questions/15983120/implementing-onvaluechange-to-a-numberpicker-in-android 

finish ()
https://www.youtube.com/watch?v=F3kaYKqnUJg 

SharedPreferences to maintain login: adapted from
https://developer.android.com/reference/android/content/SharedPreferences.html 
and https://ddrohan.github.io/wit-mad-1-2018/topic04-persistence//talk-9-persistence/persistence.pdf

Floating Action Button: adapted from: https://guides.codepath.com/android/floating-action-buttons 

Log.e() https://stackoverflow.com/questions/7959263/android-log-v-log-d-log-i-log-w-log-e-when-to-use-each-one 

Database Handler Classes and all CRUD operations: adapted from: https://www.androidhive.info/2011/11/android-sqlite-database-tutorial/ and https://ddrohan.github.io/wit-mad-1-2018/topic04-persistence//talk-91-sqlite/sqlite.pdf

onResume(): https://developer.android.com/reference/android/app/Activity.html#onResume() 

onRestart() https://developer.android.com/reference/android/app/Activity.html#onRestart() 

RecyclerView: https://developer.android.com/guide/topics/ui/layout/recyclerview.html 

LayoutInflator: https://developer.android.com/reference/android/view/LayoutInflater.html and https://stackoverflow.com/questions/3477422/what-does-layoutinflater-in-android-do

Preference manager: https://developer.android.com/reference/android/support/v7/preference/PreferenceManager.html 

 Github link:

https://github.com/katekelly76/AndroidAssignment1 
 

