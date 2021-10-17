package aat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import javax.swing.JFileChooser;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import aat.AspiceAssementToolMainWindow.ToolCallbacks;
import aat.QuestionTreeModel.Practice;

public class AspiceAssementTool implements ToolCallbacks {
    AspiceAssementToolMainWindow window;

    public static void main(String[] args) {
        AspiceAssementTool tool = new AspiceAssementTool();

        tool.run( args);
    }

    void run(String[] args) {
        
        
        
        window = new AspiceAssementToolMainWindow();

        TreeModel questionModel = new QuestionTreeModel();


        window.setQuestionModel(questionModel);
        window.addController(this);
        
        for(int i = 0 ; i<args.length;i++)
        {
            if(args[i].equals("--new"))
            {
                if(i+1<args.length) 
                {
                    newProject(new File(args[i+1]));
                }
            }
        }
        
        window.show();
    }

    @Override
    public void actionNew() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("New Project");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(window.getFrame()) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
            newProject(chooser.getSelectedFile());
        }
        else {
            System.out.println("No Selection ");
        }
    }


    public void deleteDirectory(File dir) 
      throws IOException {
        Path p= Paths.get(dir.getAbsolutePath());

        Files.walk(p)
          .sorted(Comparator.reverseOrder())
          .map(Path::toFile)
          .forEach(File::delete);

       
    }
    

    private void newProject(File selectedFile) {
     // Create a new repository
        Repository newlyCreatedRepo;
        try {
            if(selectedFile.exists())
            {
                deleteDirectory(selectedFile);
            }
            newlyCreatedRepo = FileRepositoryBuilder.create(
                new File(selectedFile.getAbsolutePath()+"/.git"));
            
            newlyCreatedRepo.create();
            /** Copy Right notice of the AutomotiveSPICE_PAM_31.pdf 
              
            
             The detailed descriptions contained in this document may be incorporated as part of any tool or other
material to support the performance of process assessments, so that this process assessment model
can be used for its intended purpose, provided that any such material is not offered for sale.
All distribution of derivative works shall be made at no cost to the recipient.
            
             
             This work shall not be offered for sale - this is done by publishing it under the GPL.
             */
            
            QuestionTreeModel questions=new QuestionTreeModel();
            QuestionTreeModel.Root r=questions.getRoot2();
            QuestionTreeModel.Level l;
            QuestionTreeModel.Process p; 
            Practice pr;
            boolean iso9001=false;
            boolean iso26262=false;
            if(iso9001)
            {
            l= r.addLevel("ISO9001 - DRAFT");
            p= l.addProcess("PLAN");
            p.addPractice("PLAN.1", "Plan the Process");
            p.addPractice("PLAN.2", "Communicate the Process");
            p.addPractice("PLAN.3", "Define AKV Matrix");
            p = l.addProcess("DO");
            p.addPractice("DO.1", "Perform the process");
            p.addPractice("DO.2", "Have the right resources");
            p = l.addProcess("CECK");
            p.addPractice("CHECK.1", "Measure the Process performance");
            p.addPractice("CHECK.2", "Customer focus");
            p.addPractice("CHECK.3", "Chances and Risks");
            p = l.addProcess("ACT");
            }
            if(iso26262)
            {
            
            l= r.addLevel("ISO26262-6 - DRAFT (the ISO contains the binding Texts)");
            p = l.addProcess("INIT");
            p.addPractice("5.4.1", "Plan activities and methods for software developement");
            p.addPractice("5.4.2", "tailor lifcycle of software development");
            p.addPractice("5.4.3", "Apply appendix C if configurable software is developed");
            p.addPractice("5.4.4", "Ensure consistency with HW and System development process");
            p.addPractice("5.4.5", "Select methods and corresponding tools, with guildines for usage");
            p.addPractice("5.4.5", "Consider criteria for modelling and programming languages");
            p.addPractice("5.4.6", "Establish design and coding guidelines");
            
            p = l.addProcess("REQ");
            p.addPractice("6.4.1","A safety requirement shall address each software element which could violate a technical safety requirement"); 
            p.addPractice("6.4.2","software safety requirements shall be derived from technical safety requirements [...]");
            p.addPractice("6.4.3","If ASIL decomposition is applied to software safety requirements, ISO26262-9 Clause 5 shall be complied with");
            p.addPractice("6.4.4","Refine HW/SW Interface initiated by ISO26262-4 Clause 7, down to the corrrect control and usage of hardware, describe safety related dependency between HW and SW");
            p.addPractice("6.4.5","Non safety related functions on the same HW shall be specfied or linked to");
            p.addPractice("6.4.6","Verification of Software Safety Requirements shall be planned");
            p.addPractice("6.4.7","HW/SW Interface specifciation shall be reviwed jointly by HW and SW development responsible");
            p.addPractice("6.4.8","softwarer safety requirements and HW/SW interface requirements shall be verfied for consitency, compliace to system design, constistency with HSI");
            p = l.addProcess("ARCH");
            p.addPractice("7.4.1","Architecture shall use appropriate level of abstraction and notation");
            p.addPractice("7.4.2","Consider: verifyability of architecture, suitable configurable software, feasibility for software design and implementation, testability and maintainaablity");
            p.addPractice("7.4.3","Architecture shall exhibit: Modularity, Encapsulation and Simplicity");
            p.addPractice("7.4.4","Software architecture shall identify all software units");
            p.addPractice("7.4.5","Software architecture shall describe static design and dynamic design aspects");
            p.addPractice("7.4.6","Software shall be categorized in new, reused with modification and reused without modififcation");
            p.addPractice("7.4.7","new and modified software shall be compliant to IS26262 - except -8 clause 12");
            p.addPractice("7.4.8","new and modified software shall be qualified according to -8 clause 12");
            p.addPractice("7.4.9","software safety requirements shall be allocated to software components and developed in accordance to the highest asil allocated");
            p.addPractice("7.4.10","Non safety cricical software shall meet the criteria for coexistance Compliant to ISO26262-9 clause 6");
            p.addPractice("7.4.11","Software partioning shall prove freefom from interference");
            p.addPractice("7.4.12","dependent failure analysis shall be carried out of the argument of freedom of interference or sufficient independence between software components is applied");
            p.addPractice("7.4.13","Safety analysis shall be carried out on software architecture level in Accordance to -9 Clause 8");
            p.addPractice("7.4.14","Necessary SW safety mechanisms applied"); 
            p.addPractice("7.4.15","Necessary error handling is applied");
            p.addPractice("7.4.16","Newly introduced Hazards introduced from SW Architecture are evaluated");
            p.addPractice("7.4.17","Resource Estimation is performed");
            p.addPractice("7.4.18","SW Archiecture is verified");
            p = l.addProcess("IMP");
            p.addPractice("8.4.2","Describe the SW Unit Design");
            p.addPractice("8.4.3","Describe Functional and internal design to the level of detail necessary for implementation");
            p.addPractice("8.4.4","Apply design principles during design and implementation");
                        p.addPractice("8.4.5","Verify the software unit design");
            p = l.addProcess("TEST");
            p.addPractice("9.4.2","Plan the unit testing");
            p.addPractice("9.4.3","Apply Methods for unit tesing");
            p.addPractice("9.4.4","Derive unit tests with selected methods");
            p.addPractice("9.4.5","Measure structural coverage of unit tests");
            p.addPractice("9.4.6","Test as closly on the target enviromentment as possible or specify subsequent tests to be carried out");
            p = l.addProcess("INT");
            p.addPractice("10.4.1","Describe the Integration Steps with their dependencies on SW/SW and SW/HW level");
            p.addPractice("10.4.2","Plan SW Integration");
            p.addPractice("10.4.3","Apply selected Methods for integration testing");
            p.addPractice("10.4.4","Derive Test cases with selected methods");
            p.addPractice("10.4.5","Evaluate completeness of the tests");
            p.addPractice("10.4.6","Measure structural coverage of tests");
            p.addPractice("10.4.7","Ensure only specified functions are included or show that the function does not impair sw safety requirements");
            p.addPractice("10.4.6","Test as closly on the target enviromentment as possible or specify subsequent tests to be carried out");
            l= r.addLevel("ISO26262-6-C - DRAFT");
            }
            
            
            
            l= r.addLevel("ASPICE_Level1");
            p = l.addProcess("ACQ.4");
            p.addPractice("ACQ.4.BP1", "Agree on and maintain joint processes, joint interfaces, and information to be exchanged");
            p.addPractice("ACQ.4.BP2", "Exchange all agreed information");
            p.addPractice("ACQ.4.BP3", "Review technical development with the supplier");
            p.addPractice("ACQ.4.BP4", "Review progress of the supplier");
            p.addPractice("ACQ.4.BP5", "Act to correct deviations.");
            
            
            
            p=l.addProcess("SYS.2");
            p.addPractice("SYS.2.BP1", "Specify system requirements");
            p.addPractice("SYS.2.BP2", "Structure system requirements");
            p.addPractice("SYS.2.BP3", "Analyze system requirements");
            p.addPractice("SYS.2.BP4", "Analyze the impact on the operating environment");
            p.addPractice("SYS.2.BP5", "Develop verification criteria");
            p.addPractice("SYS.2.BP6", "Establish bidirectional traceability");
            p.addPractice("SYS.2.BP8", "Communicate agreed system requirements");
            
            
            p=l.addProcess("SYS.3");
            p.addPractice("SYS.3.BP1", "Develop system architectural design");
            p.addPractice("SYS.3.BP2", "Allocate system requirements");
            p.addPractice("SYS.3.BP3", "Define interfaces of system elements");
            p.addPractice("SYS.3.BP4", "Describe dynamic behavior");
            p.addPractice("SYS.3.BP5", "Evaluate alternative system architectures");
            p.addPractice("SYS.3.BP6", "Establish bidirectional traceability");
            p.addPractice("SYS.3.BP7", "Ensure consistency");
            p.addPractice("SYS.3.BP8", "Communicate agreed system architectural design");
            
            
            
            
            p=l.addProcess("SYS.4");
            p.addPractice("SYS.4.BP1", "Develop system integration strategy");
            p.addPractice("SYS.4.BP2", "Develop system integration test strategy including regression test strategy");
            p.addPractice("SYS.4.BP3", "Develop specification for system integration test");
            p.addPractice("SYS.4.BP4", "Integrate system items");
            p.addPractice("SYS.4.BP5", "Select test cases");
            p.addPractice("SYS.4.BP6", "Perform system integration test");
            p.addPractice("SYS.4.BP7", "Establish bidirectional traceability");
            p.addPractice("SYS.4.BP8", "Ensure consistency");
            p.addPractice("SYS.4.BP9", "Summarize and communicate results");
            
            
            
            p=l.addProcess("SYS.5");
            p.addPractice("SYS.5.BP1", "Develop system qualification test strategy including regression test strategy");
            p.addPractice("SYS.5.BP2", "Develop specification for system qualification test");
            p.addPractice("SYS.5.BP3", "Select test cases");
            p.addPractice("SYS.5.BP4", "Test integrated system");
            p.addPractice("SYS.5.BP5", "Establish bidirectional traceability");            
            p.addPractice("SYS.5.BP6", "Ensure consistency");
            p.addPractice("SYS.5.BP7", "Summarize and communicate results");
            
            
            
            p=l.addProcess("SWE.1");
            pr=p.addPractice("SWE.1.BP1", "Specify software requirements");
            pr.addQuestion("1", "How do you create Software requirements? [Are SW Requirements created? They may be derived from Stakeholder Requirements or System Requirements/Architecture");
            pr.addQuestion("2", "Show a functional Software Requirement");
            pr.addQuestion("3", "Show a  non-functional Software Requirement");
            pr.addQuestion("4", "Show a  change control record of a software requirement");
            pr.addQuestion("5", "Show the interface requirement specification for an element [containing this interface which are specified on system level]");
            pr.addQuestion("6", "Show the software requirement specification for an element");
            
            pr=p.addPractice("SWE.1.BP2", "Structure software requirements");
            pr.addQuestion("1", "How do you structure software requirements?");
            pr.addQuestion("2", "How do you priorized Software Requiremts / assign them to releases?");
            pr=p.addPractice("SWE.1.BP3", "Analyze software requirements");
            pr.addQuestion("1", "How do you priorized Software Requiremts / assign them to releases?");
            
            pr.addQuestion("2", "How do you know the Requirements are feasible technically/economically?");
            pr.addQuestion("3", "How do you know the dependencies between the requirements?");
            pr.addQuestion("4", "Has a PO reviewed the requirements?");
            pr.addQuestion("5", "Show an analysis report containing risks identified, assumptions/constrains");
            pr=p.addPractice("SWE.1.BP4", "Analyze the impact on the operating environment");
            pr.addQuestion("1", "How do you analyze Software Requirement's impact on the system environment?");
            pr.addQuestion("2", "Has an Architect reviewed the requirements?");
            
            pr=p.addPractice("SWE.1.BP5", "Develop verification criteria");
            pr.addQuestion("1", "How do you create verfification criteria of software requirements?");
            pr.addQuestion("2", "How many requirements have a verfication criteria?");
            pr.addQuestion("3", "Has a SWE.6 responsible reviewed the requirements?");
            pr=p.addPractice("SWE.1.BP6", "Establish bidirectional traceability");
            pr.addQuestion("1", "How do ceate tracablity to System Requirements or Stakeholder Requirements?");
            pr.addQuestion("2", "Demo that the tracability is bi drectional?");
            pr.addQuestion("3", "What is the link coverage?");
            pr.addQuestion("4", "How do you avoid reduncancy?");
            pr.addQuestion("5", "Demonstrate that the linkage is being reviewed! [plan, review record, ticket to change the link]");
            pr=p.addPractice("SWE.1.BP7", "Ensure consistency");
            pr.addQuestion("1", "How do ensure consistency between Software Requirements  and the System/Stakeholder requirements and the System architecture?");
            pr.addQuestion("2", "Has a developer reviewed the requirements?");
            pr=p.addPractice("SWE.1.BP8", "Communicate agreed software requirements");
            pr.addQuestion("1", "How do you commnicte the agreed software requirements? [communication record?]");
            p=l.addProcess("SWE.2");
            pr=p.addPractice("SWE.2.BP1", "Develop software architectural design");
            pr.addQuestion("1", "Show the Software Architectural design");
            pr.addQuestion("2", "Show the red-line of the architecture. Do you employ hierachies?");
            pr.addQuestion("3", "To which level of detail is the softare architecture shown?");
            pr=p.addPractice("SWE.2.BP2", "Allocate software requirements.");
            pr.addQuestion("1", "How do you allocate requirements to software elements?");
            pr=p.addPractice("SWE.2.BP3", "Define interfaces of software elements.");
            pr.addQuestion("1", "How do you define interfaces bewteen the software elements?");
            pr=p.addPractice("SWE.2.BP4", "Describe dynamic behavior");
            pr.addQuestion("1", "How do you show dynamic behaviour of the software elements?");
            pr.addQuestion("2", "Show diagrams/specification for startup and shutdown of the software");
            pr.addQuestion("3", "Show diagrams/specification for startup and shutdown of the software");
            pr.addQuestion("4", "How do you takle different modes of operation? [diagnosis, degration, normal operation?]");
            pr.addQuestion("5", "How do you consider different loads of RAM and CPU?");
            pr=p.addPractice("SWE.2.BP5", "Define resource consumption objectives");
            pr.addQuestion("1", "How do you keep track of the resource consumption?");
            pr.addQuestion("2", "How do you react if more resources are used than expected?");
            pr.addQuestion("3", "Which resources do you monitor? [RAM, CPU, ROM, NVRAM ...]");
            pr=p.addPractice("SWE.2.BP6", "Evaluate alternative software architectures");
            pr.addQuestion("1", "Did you evaluate alternative software architectures?");
            pr.addQuestion("2", "Which criterias do you apply?");
            pr.addQuestion("3", "Do you have results of make of buy analyisis?");
            pr=p.addPractice("SWE.2.BP7", "Establish bidirectional traceability");
            pr.addQuestion("1", "How do you establish tracability to requirements?");
            pr.addQuestion("2", "Demonstrate the bi-drectional nature of the tracability");
            pr.addQuestion("3", "Is there a coverage report?");
            pr.addQuestion("4", "Did tracability support your impact analysis? [if yes, show it]");
            pr=p.addPractice("SWE.2.BP8", "Ensure consistency");
            pr.addQuestion("1", "How do you ensure consistency of the architecture?");
            pr.addQuestion("2", "Can you show review records? What is the review coverage?");
            pr= p.addPractice("SWE.2.BP9", "Communicate agreed software architectural design");
            pr.addQuestion("1", "Which parties are affected by the software architecture?");
            pr.addQuestion("2", "How do you communicate the architecture? [is there a communication record?]");
            pr.addQuestion("3", "If the architetcure is changed. How do you ensure all the involved parties are aware of it?");
            
            p=l.addProcess("SWE.3");
            pr=p.addPractice("SWE.3.BP1", "Develop software detailed design");
            pr.addQuestion("1", "How do you specify the detailed design?");
            pr.addQuestion("2", "How do you reflect a specific software requirement?");
            pr.addQuestion("3", "Do you tread functional and non functional requirements differently?");
            pr.addQuestion("4", "How do you ensure the design has been created for all software units defined in the architetcure?");
            pr.addQuestion("5", "What happens if something is not defined in the architetcure? Can this happen?");
            p.addPractice("SWE.3.BP2", "Define interfaces of software units");
            pr.addQuestion("1", "How do you identify interfaces of a SW Unit?");
            pr.addQuestion("2", "How do you specifcy interfaces to SW Unit?");
            pr.addQuestion("3", "Is this the same for all? [What about OS Interfaces or components which do not use the default way of communication?]");
            pr.addQuestion("4", "How many interfaces are specified?");
            p.addPractice("SWE.3.BP3", "Describe dynamic behavior");
            pr.addQuestion("1", "Do you descirbe dynamic behaviour of SW Units?");
            pr.addQuestion("2", "For which components do you describe this in the SW Detailed Design? [interal, complixty]");
            p.addPractice("SWE.3.BP4", "Evaluate software detailed design");
            pr.addQuestion("1", "How do you evaluate the detailed design?");
            pr.addQuestion("2", "Which criteria do you apply [interoperability, interaction, criticality, technical complexity, risks and testability]?");
            pr.addQuestion("3", "How/Do you use this output in SW Unit Verificiation");
            p.addPractice("SWE.3.BP5", "Establish bidirectional traceability");
            pr.addQuestion("1", "Demonstrate the bi-directional Tracability to SW Requirements");
            pr.addQuestion("2", "Demonstrate the bi-directional Tracability to architetcure");
            pr.addQuestion("3", "Demonstrate the bi-directional Tracability to SW Detailed Design and SW Units");
            pr.addQuestion("4", "How do you avoid redundancy?");
            pr.addQuestion("5", "Do you have a link coverage report? [no ok if, foolproof by design]");
            pr.addQuestion("6", "How does linkage support you - can you demonstrate it? [coverage, consistency, impact analysis]");
            p.addPractice("SWE.3.BP6", "Ensure consistency");
            pr.addQuestion("1", "How do you ensure constency?");
            pr.addQuestion("2", "Can you present a review record which demonstrates that consistency was checked? [e.g. if it as a check item and ok or even nok]");
            p.addPractice("SWE.3.BP7", "Communicate agreed software detailed design");
            pr.addQuestion("1", "Which parties are affected by the software detailed design?");
            pr.addQuestion("2", "How do you communicate the architecture? [is there a communication record?]");
            pr.addQuestion("3", "If the detailed design is changed. How do you ensure all the involved parties are aware of it?");
            p.addPractice("SWE.3.BP8", "Develop software units");
            pr.addQuestion("1", "How do you develop the actual software units?");
            pr.addQuestion("2", "Is there a review record?");
            pr.addQuestion("3", "Do you follow coding guildlines? How are they enforced?");
            

            p=l.addProcess("SWE.4");
            p.addPractice("SWE.4.BP1", "Develop software unit verification strategy including regression strategy");
            
            p.addPractice("SWE.4.BP2", "Develop criteria for unit verification");
            p.addPractice("SWE.4.BP3", "Perform static verification of software units");
            p.addPractice("SWE.4.BP4", "Test software units");
            p.addPractice("SWE.4.BP5", "Establish bidirectional traceability");
            p.addPractice("SWE.4.BP6", "Ensure consistency");
            p.addPractice("SWE.4.BP7", "Summarize and communicate results");
            
            p=l.addProcess("SWE.5");
            p.addPractice("SWE.5.BP1", "Develop software integration strategy");
            p.addPractice("SWE.5.BP2", "Develop software integration test strategy including regression test strategy");
            p.addPractice("SWE.5.BP3", "Develop specification for software integration test");
            p.addPractice("SWE.5.BP4", "Integrate software units and software items");
            p.addPractice("SWE.5.BP5", "Select test cases");
            p.addPractice("SWE.5.BP6", "Perform software integration test");
            p.addPractice("SWE.5.BP7", "Establish bidirectional traceability");
            p.addPractice("SWE.5.BP8", "Ensure consistency");
            p.addPractice("SWE.5.BP9", "Summarize and communicate results");
            
            p=l.addProcess("SWE.6");
            p.addPractice("SWE.6.BP1", "Develop software qualification test strategy including regression test strategy");
            p.addPractice("SWE.6.BP2", "Develop specification for software qualification test");
            p.addPractice("SWE.6.BP3", "Select test cases. Select test cases from the software test specification");
            p.addPractice("SWE.6.BP4", "Test integrated software");
            p.addPractice("SWE.6.BP5", "Establish bidirectional traceability");
            p.addPractice("SWE.6.BP6", "Ensure consistency");
            p.addPractice("SWE.6.BP7", "Summarize and communicate results");
            
            
            p=l.addProcess("SUP.1");
            p.addPractice("SUP.1.BP1", "Develop a project quality assurance strategy");
            p.addPractice("SUP.1.BP2", "Assure quality of work products");
            p.addPractice("SUP.1.BP3", "Assure quality of process activities");
            p.addPractice("SUP.1.BP4", "Summarize and communicate quality assurance activities and results");
            p.addPractice("SUP.1.BP5", "Ensure resolution of non-conformances");
            p.addPractice("SUP.1.BP7", "Implement an escalation mechanism");
            
            
            
            
            
            p=l.addProcess("SUP.8");
            p.addPractice("SUP.8.BP1", "Develop a configuration management strategy");
            p.addPractice("SUP.8.BP2", "Identify configuration items");
            p.addPractice("SUP.8.BP3", "Establish a configuration management system");
            p.addPractice("SUP.8.BP4", "Establish branch management");
            p.addPractice("SUP.8.BP5", "Control modifications and releases");
            p.addPractice("SUP.8.BP6", "Establish baselines.");
            p.addPractice("SUP.8.BP7", "Report configuration status");
            p.addPractice("SUP.8.BP8", "Verify the information about configured items");
            p.addPractice("SUP.8.BP9", "Manage the storage of configuration items and baselines");
            
            
            
            
            p=l.addProcess("SUP.9");
            p.addPractice("SUP.9.BP1", "Develop a problem resolution management strategy");
            p.addPractice("SUP.9.BP2", "Identify and record the problem");
            p.addPractice("SUP.9.BP3", "Record the status of problems");
            p.addPractice("SUP.9.BP4", "Diagnose the cause and determine the impact of the problem");
            p.addPractice("SUP.9.BP5", "Authorize urgent resolution action");
            p.addPractice("SUP.9.BP6", " Raise alert notifications");
            p.addPractice("SUP.9.BP7", "Initiate problem resolution");
            p.addPractice("SUP.9.BP8", "Track problems to closure");
            p.addPractice("SUP.9.BP9", "Analyze problem trends");
            
                   
            
            
            l.addProcess("SUP.10");
            p.addPractice("SUP.10.BP1", "Develop a change request management strategy");
            p.addPractice("SUP.10.BP2", "Identify and record the change requests");
            p.addPractice("SUP.10.BP3", "Record the status of change requests");
            p.addPractice("SUP.10.BP4", "Analyze and assess change requests");
            p.addPractice("SUP.10.BP5", "Approve change requests before implementation.");
            p.addPractice("SUP.10.BP6", "Review the implementation of change requests");
            p.addPractice("SUP.10.BP7", "Track change requests to closure");
            p.addPractice("SUP.10.BP8", "Establish bidirectional traceability.");
            
            
            
            l.addProcess("MAN.3");
            p.addPractice("MAN.3.BP1", "Define the scope of work");
            p.addPractice("MAN.3.BP2", "Define project life cycle");
            p.addPractice("MAN.3.BP3", "Evaluate feasibility of the project");
            p.addPractice("MAN.3.BP4", "Define, monitor and adjust project activities");
            p.addPractice("MAN.3.BP5", " Define, monitor and adjust project estimates and resources");
            p.addPractice("MAN.3.BP6", "Ensure required skills, knowledge, and experience");
            p.addPractice("MAN.3.BP7", "Identify, monitor and adjust project interfaces and agreed commitments");
            p.addPractice("MAN.3.BP8", "Define, monitor and adjust project schedule.");
            p.addPractice("MAN.3.BP9", "Ensure consistency");
            p.addPractice("MAN.3.BP10", "Review and report progress of the project");
            
         
            l= r.addLevel("ASPICE_Level2");
            
            p=l.addProcess("PA 2.1");
            p.addPractice("GP 2.1.1", "Identify the objectives for the performance of the process");
            p.addPractice("GP 2.1.2", "Plan the performance of the process to fulfill the identified objectives");
            p.addPractice("GP 2.1.3", "Monitor the performance of the process against the plans");
            p.addPractice("GP 2.1.4", "Adjust the performance of the process");
            p.addPractice("GP 2.1.5", "Define responsibilities and authorities for performing the process");
            p.addPractice("GP 2.1.6", "Identify, prepare, and make available resources to perform the process according to plan");
            p.addPractice("GP 2.1.7", "Manage the interfaces between involved parties.");
            p=l.addProcess("PA 2.2");
            
            p.addPractice("GP 2.2.1","Define the requirements for the work products.");
            p.addPractice("GP 2.2.2","Define the requirements for documentation and control of the work products.");
            p.addPractice("GP 2.2.3","Identify, document and control the work products");
            p.addPractice("GP 2.2.4","Review and adjust work products to meet the defined requirements.");
            
            
            l= r.addLevel("ASPICE_Level3");
            
            
            p=l.addProcess("PA 3.1");            
            p.addPractice("GP 3.1.1","Define and maintain the standard process that will support the deployment of the defined process.");
            p.addPractice("GP 3.1.2","Determine the sequence and interaction between processes so that they work as an integrated system of processes.");
            p.addPractice("GP 3.1.3","Identify the roles and competencies for performing the standard process.");
            p.addPractice("GP 3.1.4","Identify the required infrastructure and work environment for performing the standard process.");
            p.addPractice("GP 3.1.4","Determine suitable methods to monitor the effectiveness and suitability of the standard process.");
           
            
            
            p=l.addProcess("PA 3.2");
            p.addPractice("GP 3.2.1", "Deploy a defined process that satisfies the context specific requirements of the use of the standard process.");
            p.addPractice("GP 3.2.1", "Assign and communicate roles, responsibilities and authorities for performing the defined process.");
            p.addPractice("GP 3.2.1", " Ensure necessary competencies for performing the defined process.");
            p.addPractice("GP 3.2.1", "Provide resources and information to support the performance of the defined process.");
            p.addPractice("GP 3.2.1", "Provide adequate process infrastructure to support the performance of the defined process.");            
            p.addPractice("GP 3.2.1", "Collect and analyze data about performance of the process to demonstrate its suitability and effectiveness.");
            
            window.setQuestionModel(questions);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  
        
    }

    @Override
    public void actionSave() {
        // TODO Auto-generated method stub

    }

    @Override
    public void actionOpen() {
        // TODO Auto-generated method stub

    }

}


