#include <stdio.h>
#include <stdlib.h>
#include <string.h>
struct files{
    char *name;
    char *text;
};
int countOccurrences(char string[], char word[]){
    int i, j, found, count;
    int stringLen, searchLen;
    stringLen = strlen(string);      
    searchLen = strlen(word);
    count = 0;
    for(i=0; i <= stringLen-searchLen; i++){

        found = 1;
        for(j=0; j<searchLen; j++){
            if(string[i + j] != word[j]){
                found = 0;
                break;
            }
        }
        if(found == 1){
            count++;
        }
    }
    return count;
}
void CreateFile(char line[],int *sequence,struct files **array) {
    int count = 10;
    int i;
    if (*(line + 8) == 'n') {                                                           //Lenght of name
        while (*(line + count) != ' ') {
            count++;
        }
        array[*sequence]->name = malloc(sizeof(char) * (count - 9));
        for (i = 0; i < (count - 10); ++i) {                                     //Assign name of structure
            array[*sequence]->name[i] = line[i + 10];
        }
        array[*sequence]->name[count - 10] = '\0';

        int count2 = count + 2;
        if (*(line + count2) == 't') {                                                  //Length of text
            if (*(line + count2 + 1) == '\n') {
                array[*sequence]->text = malloc(sizeof(char)*11);
                strcpy(array[*sequence]->text, "Empty File\0");
            } else {
                count2 = count2 + 2;
                while (*(line + count2) != '\0') {
                    count2++;
                }
                array[*sequence]->text = malloc(sizeof(char) * ((count2 - count) - 4));
                for (i = 0; i < (count2 - count - 5); ++i) {                           //Assign the text
                    array[*sequence]->text[i] = line[count + 4 + i];
                }
                array[*sequence]->text[count2 - count - 5] = '\0';
            }
        }
    } else if (*(line + 8) == 't') {
        if (*(line + 10) == '-') {
            array[*sequence]->text = malloc(sizeof(char)*11);
            strcpy(array[*sequence]->text, "Empty File\0");
            if (*(line+11)=='n'){
                count=count+3;
                while (*(line+count)!='\n'){
                    count++;
                }
                array[*sequence]->name=malloc(sizeof(char)*(count-12));
                for (i = 0; i <(count-12) ; ++i) {
                    array[*sequence]->name[i]=line[i+13];
                }
                array[*sequence]->name[count-13]='\0';
            }
        } else {
            while (*(line + count) != '-') {
                count++;
            }

            array[*sequence]->text = malloc(sizeof(char) * (count - 9));
            for (i = 0; i < (count - 11); ++i) {                                     //Assign name of structure
                array[*sequence]->text[i] = line[i + 10];
            }
            array[*sequence]->text[count - 11] = '\0';

            int count2 = count + 1;
            if (*(line + count2) == 'n') {                                                  //Length of text
                count2 = count2 + 2;
                while (*(line + count2) != '\0') {
                    count2++;
                }
                array[*sequence]->name = malloc(sizeof(char) * ((count2 - count) - 3));

                for (i = 0; i < (count2 - count - 4); ++i) {                           //Assign the text
                    array[*sequence]->name[i] = line[count + 3 + i];
                }
                array[*sequence]->name[count2 - count - 4] = '\0';
            }
        }
    }
}
void DeleteFile(char line[],int *number_of_files, struct files **array){
    char *control_array;
    int i=10;
    int j;
    int k;
    while(*(line+i)!='\n'){
        i++;
    }
    control_array=malloc(sizeof(char)*(i-9));
    for (j = 0; j <(i-10) ; ++j) {
        control_array[j]=*(line+j+10);
    }
    control_array[i-10]='\0';
    for (k = 0; k <*number_of_files ; ++k) {
        if (strcmp(control_array, array[k]->name) == 0) {
            array[k]->name = NULL;
            array[k]->text = NULL;
        }
    }
}
void RemoveText(char line[],int *number_of_files,struct files **array) {
    char *control_array;
    char *number;
    char *length;
    char *New_string;
    int i = 10;
    int t = 0;
    int j;
    int k = 0;
    int l;
    int n=0;
    int m=0;
    int q=0;
    int z=0;
    while (*(line + i) != ' ') {
        i++;
    }

    control_array = malloc(sizeof(char) * (i - 9));
    for (j = 0; j < (i - 10); ++j) {
        control_array[j] = *(line + j + 10);
    }
    control_array[i - 10] = '\0';
    i=i+4;
    while (*(line+i)!=' '){
        t++;
        i++;
    }
    number=malloc(sizeof(char)*(t+1));
    for (l = 0; l <t ; ++l) {
        number[l]=*(line+i-t+l);

    }
    number[t]='\0';
    i=i+4;
    while (*(line+i)!='\0'){
        k++;
        i++;
    }
    length=malloc(sizeof(char)*k);
    for (n ; n <(k-1) ; ++n) {
        length[n]=*(line+i-k+n);
    }
    length[k-1]='\0';
    int number1=atoi(number);
    int length1=atoi(length);
    for (m = 0; m < *number_of_files; ++m) {
        if (strcmp(array[m]->name,control_array)==0){
            New_string=malloc(sizeof(char)* (int) sizeof(array[m]->text));
            for (q = 0; q <number1 ; ++q) {
                New_string[q]=array[m]->text[q];
            }
            for (z = (number1+length1); z < (int)strlen(array[m]->text) ; ++z) {

                New_string[q]=array[m]->text[z];
                q++;
            }
            New_string[q]='\0';
            array[m]->text=New_string;

        }

    }
}
void AppendText(char line[],int *number_of_files,struct files **array) {
    char *control_array;
    char *new_string;
    int count = 10;
    int count2 =0;
    int i,j,k;
    if (*(line + 8) == 'n') {                                                           //Lenght of name
        while (*(line + count) != ' ') {
            count++;
        }
        control_array = malloc(sizeof(char) * (count - 9));
        for (i = 0; i < (count - 10); ++i) {                                     //Assign name of structure
            control_array[i] = line[i + 10];
        }
        control_array[count - 10] = '\0';
        count2=count+2;

        if (*(line+count2)=='t'){
            count2=count2+2;
            while(*(line+count2)!='\0'){
                count2++;
            }

            new_string=malloc(sizeof(char)*(count2-count-4));
            for (j = 0; j <(count2-count-4) ; ++j) {
                new_string[j]=*(line+count+4+j);
            }
            new_string[count2-count-4]='\0';

        }
    }
    else if(*(line+8)=='t'){
        while (*(line + count) != '-') {
            count++;
        }
        new_string = malloc(sizeof(char) * (count - 9));
        for (i = 0; i < (count - 10); ++i) {                                     //Assign name of structure
            new_string[i] = line[i + 10];
        }
        new_string[count - 10] = '\0';
        count2=count+2;

        if (*(line+count2-1)=='n'){
            count2=count2+1;

            while(*(line+count2)!='\0'){
                count2++;
            }
            control_array=malloc(sizeof(char)*(count2-count-4));
            for (j = 0; j <(count2-count-4) ; ++j) {
                control_array[j]=*(line+count+3+j);
            }
            control_array[count2-count-4]='\0';
        }
    }


    for (k = 0; k < *number_of_files; ++k) {
        if (strcmp(array[k]->name,control_array)==0){
            if (strcmp(array[k]->text,"Empty File\0")==0){
                array[k]->text=(char *)realloc(array[k]->text,strlen(new_string));
                array[k]->text=new_string;
            }
            else{
            array[k]->text=(char *)realloc(array[k]->text,strlen(array[k]->text)+strlen(control_array));
            strcat(array[k]->text,new_string);
            }
        }
    }
}
void ReplaceWord(char line[],int *sequence,struct files **array){
    char *control_array,*new_word,*old_word;
    int count=11;
    int i,j;
    int count2=0,count3=0;
    if ((*(line+9)=='n')&& (*(line+10)!='w')){
        while (*(line+count)!='-'){
            count++;
        }
        control_array=malloc(sizeof(char)*(count-11));

        for (i = 0; i <(count-11) ; ++i) {
            control_array[i]=line[i+11];
        }
        control_array[count-12]='\0';
        count++;
        if (*(line+count)=='o'){
            count=count+3;
            while (*(line+count)!='-'){
                count++;
                count2++;
            }
            old_word=malloc(sizeof(char)*count2);
            for (i = 0; i <(count-count2-17) ; ++i) {
                old_word[i]=*(line+count-count2+i);
            }
            old_word[count2-1]='\0';
            count++;
            if (*(line+count)=='n'){
                count=count+3;
                while (*(line+count)!='\0'){
                    count++;
                    count3++;
                }

                new_word=malloc(sizeof(char)*(count3));
                for (i = 0; i <count3 ; ++i) {
                    new_word[i]=*(line+count-count3+i);
                }
                new_word[count3-1]='\0';
            }
        }
        else if(*(line+count)=='n'){
            count=count+3;
            while (*(line+count)!='-'){
                count++;
                count2++;
            }
            new_word=malloc(sizeof(char)*count2);
            for (i = 0; i <(count-count2-17) ; ++i) {
                new_word[i]=*(line+count-count2+i);
            }
            new_word[count2-1]='\0';
            count++;
            if (*(line+count)=='o'){
                count=count+3;
                while (*(line+count)!='\0'){
                    count++;
                    count3++;
                }
                old_word=malloc(sizeof(char)*(count3));
                for (i = 0; i <count3 ; ++i) {
                    old_word[i]=*(line+count-count3+i);
                }
                old_word[count3-1]='\0';
            }
        }
    }
    else if (*(line+9)=='o'){
        count=12;
        while (*(line+count)!='-'){
            count++;
        }
        old_word=malloc(sizeof(char)*(count-12));

        for (i = 0; i <(count-11) ; ++i) {
            old_word[i]=line[i+12];
        }
        old_word[count-13]='\0';
        count=count+2;
        if (*(line+count)=='w'){
            count=count+2;
            while (*(line+count)!='-'){
                count++;
                count2++;
            }
            new_word=malloc(sizeof(char)*count2);
            for (i = 0; i <count2 ; ++i) {
                new_word[i]=*(line+count-count2+i);
            }
            new_word[count2-1]='\0';
            count++;
            if (*(line+count)=='n'){
                count=count+2;
                while (*(line+count)!='\0'){
                    count++;
                    count3++;
                }
                control_array=malloc(sizeof(char)*count3);
                for (i = 0; i <count3 ; ++i) {
                    control_array[i]=*(line+count-count3+i);
                }
                control_array[count3-1]='\0';
            }
        }
        else if (*(line+count-1)=='n'){
            count++;
            while (*(line+count)!='-'){
                count++;
                count2++;
            }
            control_array=malloc(sizeof(char)*count2);
            for (i = 0; i <(count2-1) ; ++i) {
                control_array[i]=*(line+count-count2+i);
            }
            control_array[count2-1]='\0';
            count++;
            if (*(line+count)=='n'){
                count=count+3;
                while (*(line+count)!='\0'){
                    count++;
                    count3++;
                }
                new_word=malloc(sizeof(char)*(count3+1));
                for (i = 0; i <count3 ; ++i) {
                    new_word[i]=*(line+count-count3+i);
                }
                new_word[count3-1]='\0';
            }
        }
    }
    else if ((*(line+9)=='n')&&(*(line+10))=='w'){
        count=12;
        while (*(line+count)!='-'){
            count++;
        }
        new_word=malloc(sizeof(char)*(count-12));

        for (i = 0; i <(count-11) ; ++i) {
            new_word[i]=line[i+12];
        }
        new_word[count-13]='\0';
        count=count+2;
        if (*(line+count)=='w'){
            count=count+2;
            while (*(line+count)!='-'){
                count++;
                count2++;
            }
            old_word=malloc(sizeof(char)*count2);
            for (i = 0; i <count2 ; ++i) {
                old_word[i]=*(line+count-count2+i);
            }
            old_word[count2-1]='\0';
            count++;
            if (*(line+count)=='n'){
                count=count+2;
                while (*(line+count)!='\0'){
                    count++;
                    count3++;
                }
                control_array=malloc(sizeof(char)*count3);
                for (i = 0; i <count3 ; ++i) {
                    control_array[i]=*(line+count-count3+i);
                }
                control_array[count3]='\0';
            }
        }
        else if (*(line+count-1)=='n'){
            count++;

            while (*(line+count)!='-'){
                count++;
                count2++;
            }
            control_array=malloc(sizeof(char)*count2);
            for (i = 0; i <(count2-1) ; ++i) {

                control_array[i]=*(line+count-count2+i);

            }
            control_array[count2-1]='\0';
            count++;
            if (*(line+count-count2-3)=='n'){
                count=count+3;
                while (*(line+count)!='\0'){
                    count++;
                    count3++;
                }
                old_word=malloc(sizeof(char)*(count3+1));
                for (i = 0; i <count3 ; ++i) {
                    old_word[i]=*(line+count-count3+i);
                }
                old_word[count3-1]='\0';
            }
        }
    }
    for (j = 0; j <(*sequence) ; ++j) {
        if (strcmp(array[j]->name,control_array)==0){
            char *new_text;
            int i, cnt = 0;
            char *string;
            string=array[j]->text;
            int new_word_len = strlen(new_word);
            int old_word_len = strlen(old_word);
            for (i = 0; string[i] != '\0'; i++){
                if (strstr(&string[i], old_word) == &string[i])
                {
                    cnt++;
                    i += old_word_len - 1;
                }
            }
            new_text = (char *)malloc(i + cnt * (new_word_len - old_word_len) + 1);
            i = 0;
            while (*string)
            {
                if (strstr(string, old_word) == string)
                {
                    strcpy(&new_text[i], new_word);
                    i += new_word_len;
                    string += old_word_len;
                }
                else
                    new_text[i++] = *string++;
            }
            new_text[i] = '\0';
            array[j]->text=new_text;
        }
    }
}
void PrintFilesDatas(char line[],int *number_of_files,struct files **array,FILE *outputFile) {
    char *word;
    char *gecici_extension;
    char *new_text;
    if (*(line + 7) == 'a') {
        int i;
        int file_no = 0;
        for (i = 0; i < *number_of_files; i++) {
            if (array[i]->name == NULL) {
                file_no++;
                continue;
            } else {

                fprintf(outputFile,"Filename %d :    %s\n", file_no, array[i]->name);
            }
        }
    } else if (*(line + 7) == 'e') {
        char *given_extension;
        int i = 9;
        int j, k, l;
        int q = 0;
        int file_no = 0;
        int many_file =0;
        while (*(line + i) != '\0') {
            i++;
        }
        given_extension = malloc(sizeof(char) * (i - 9));
        for (j = 0; j < (i - 10); ++j) {
            given_extension[j] = *(line + 9 + j);
        }
        given_extension[j] = '\0';
        for (k = 0; k < (*number_of_files); ++k) {
            if (array[k]->name == NULL) {
                many_file;
                continue;
            } else {
                while ((array[k]->name[q]) != '.') {
                    q++;
                }
                gecici_extension = malloc(sizeof(char) * (strlen(array[k]->name) - q));
                for (l = 0; l < ((strlen(array[k]->name) - q - 1)); ++l) {
                    gecici_extension[l] = array[k]->name[q + 1 + l];
                }
                gecici_extension[q - 2] = '\0';
                q = 0;
                int m;
                file_no = 0;
                if (strcmp(gecici_extension, given_extension) == 0) {
                    many_file++;
                }
            }
        }
        for (k = 0; k < (*number_of_files); ++k) {
            if (array[k]->name == NULL) {
                file_no++;
                continue;
            } else {
                while ((array[k]->name[q]) != '.') {
                    q++;
                }
                gecici_extension = malloc(sizeof(char) * (strlen(array[k]->name) - q));
                for (l = 0; l < ((strlen(array[k]->name) - q - 1)); ++l) {
                    gecici_extension[l] = array[k]->name[q + 1 + l];
                }
                gecici_extension[q - 2] = '\0';
                q = 0;
                int m;
                file_no = 1;
                if (strcmp(gecici_extension, given_extension) == 0) {
                    fprintf(outputFile, "Filename %d: ", k + 1);
                    file_no++;
                    for (m = 0; m < strlen(array[k]->name); ++m) {
                        if (array[k]->name[m] == '.') {
                            file_no++;
                            break;
                        } else {
                            fprintf(outputFile, "%c", array[k]->name[m]);
                        }
                    }
                    fprintf(outputFile, "\n");
                    if (many_file>= 2) {
                        fprintf(outputFile, "Text: %s\n", array[k]->text);
                    } else {
                        fprintf(outputFile, "Text: %s", array[k]->text);

                    }
                }
            }
        }
    }
        else if (*(line+7)=='c'){
            int x,y;
            int num=0;
            for (x = 0; x <*number_of_files ; ++x) {
                if (array[x]->name==NULL){
                    ++num;
                    continue;
                }
                else {
                    ++num;
                    fprintf(outputFile,"Num: %d\n", num);
                    fprintf(outputFile,"Name: ");
                    for (y = 0; y < strlen(array[x]->name); ++y) {
                        if (array[x]->name[y] == '.') {
                            break;
                        } else {
                            fprintf(outputFile,"%c", array[x]->name[y]);
                        }
                    }
                    fprintf(outputFile,"\nText: %s\n", array[x]->text);
                }
            }
        }
        else if (*(line+7)=='n'){
            char *control_array;
            int i=9;
            int j,k,m;
            while(*(line+i)!=' '){
                i++;
            }
            control_array=malloc(sizeof(char)*(i-9));
            for (j = 0; j <(i-9) ; ++j) {
                control_array[j]=*(line+9+j);
            }
            control_array[i-9]='\0';
            i++;
            if (*(line+i+1)=='t'){
                for (k = 0; k <*number_of_files ; ++k) {
                    if (strcmp(array[k]->name,control_array)==0){

                        fprintf(outputFile,"Text: %s\n",array[k]->text);
                    }
                    else{
                        continue;
                    }
                }
            }
            else if (*(line+i+2)=='s'){
                int number_of_sentences=0;
                int n,l;
                for (n = 0; n <(*number_of_files) ; ++n) {
                    if (strcmp(array[n]->name,control_array)==0){
                        for (l = 0; l < strlen(array[n]->text); ++l) {
                            if (array[n]->text[l]=='.'){
                                number_of_sentences++;
                            }
                            else if (array[n]->text[l]=='!'){
                                number_of_sentences++;
                            }
                            else if (array[n]->text[l]=='?'){
                                number_of_sentences++;
                            }
                            else{
                                continue;
                            }
                        }
                        fprintf(outputFile,"Text: %s\n",array[n]->text);
                        fprintf(outputFile,"Number of Sentences: %d\n",number_of_sentences);
                    }
                    else{
                        continue;
                    }
                }
            }
            else if(*(line+i+2)=='w'){
                int length_of_word=0;
                int l;
                while (*(line+i+4)!='\0'){
                    length_of_word++;
                    i++;
                }
                word=malloc(sizeof(char)*length_of_word);

                for (l = 0; l <(length_of_word-1) ; ++l) {
                    word[l]=*(line+i-length_of_word+l+4);
                }
                word[length_of_word-1]='\0';
                for (m = 0; m <(*number_of_files) ; ++m) {
                    if (strcmp(array[m]->name,control_array)==0){
                        int v=  countOccurrences(array[m]->text, word);
                        fprintf(outputFile,"Text: %s\n",array[m]->text);
                        fprintf(outputFile,"Number of Occurrence of \"%s\" : %d\n",word,v);
                    }
            }
            }
        }
}
int main(int args,char *argv[]) {
    int i;
    int j;
    FILE *inputFile = fopen(argv[1], "r");                                              //Open input File
    FILE *outputFile;
    outputFile=fopen("output.txt","w");
    char line[500];                                                                     //Line of input file
    int number_of_files=0;
    while (fgets(line, 500, inputFile) != NULL) {                                       //Find number of files
        if (*line == 'c') {
            number_of_files++;
        }
    }
    struct files **All_files=(struct files**)malloc(sizeof(struct files*) * (number_of_files));         //Array of all Files
    for (i = 0; i <number_of_files ; ++i) {
        All_files[i]=(struct files*)malloc(sizeof(struct files));
    }
    rewind(inputFile);
    int sequence_of_Create=0;                                                            //Sequence of Structure
    while (fgets(line,500,inputFile)!=NULL){                                             //Reading input file for Commands
        if (*line=='c'){                                                                 //Create Command
            CreateFile(line,&sequence_of_Create,All_files);
            sequence_of_Create++;

            fprintf(outputFile,"%s",line);
        }
        else if(*line == 'd'){
            DeleteFile(line,&number_of_files,All_files);
            fprintf(outputFile,"%s",line);
        }
        else if (*(line+2)=='m'){
            RemoveText(line,&sequence_of_Create,All_files);
            fprintf(outputFile,"%s",line);
        }
        else if(*(line)=='a') {
            AppendText(line, &sequence_of_Create, All_files);
            fprintf(outputFile,"%s",line);
        }
        else if(*(line)=='p'){
            fprintf(outputFile,"%s",line);
            PrintFilesDatas(line,&sequence_of_Create,All_files,outputFile);
        }
        else if(*(line+2)=='p'){
            ReplaceWord(line,&sequence_of_Create,All_files);
            fprintf(outputFile,"%s",line);
        }
    }
    fclose(inputFile);
    fclose(outputFile);
    return 0;
}
