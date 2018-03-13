//
//  RecordsViewController.swift
//  Me
//
//  Created by Jamal on 27/02/2018.
//  Copyright © 2018 Forus. All rights reserved.
//

import UIKit

var recordsVC = RecordsViewController()

class RecordsViewController: UITableViewController {
    let model = RecordsModel()
    
    @IBOutlet weak var nameLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        recordsVC = self
        
//        recordsVC.nameLabel.text = "testing"
//        recordsLabel.text = model.labelText
    }
    
    @IBAction func setName(_ sender: Any) {
        let alertController = UIAlertController(title: "Set Name", message: "", preferredStyle: .alert)

        let confirmAction = UIAlertAction(title: "Set", style: .default) { (_) in
//            let amount = alertController.textFields?[0].text
            self.model.updateName(name: (alertController.textFields?[0].text)!)
//            self.setLabel(name: (alertController.textFields?[0].text)!)

            //            self.model.performEtherTransaction(destination: address, amount: amount!)
        }

        //the cancel action doing nothing
        let cancelAction = UIAlertAction(title: "Cancel", style: .cancel) { (_) in }

        //adding textfields to our dialog box
        alertController.addTextField { (textField) in
            textField.placeholder = "Set Name"
        }

        // set temporary fields
        //        alertController.textFields?[0].text = model.hardcodedAmount

        //adding the action to dialogbox
        alertController.addAction(confirmAction)
        alertController.addAction(cancelAction)

        //finally presenting the dialog box
        self.present(alertController, animated: true, completion: nil)
    }
    
    func setLabel(name: String) {
        recordsVC.nameLabel.text = name
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
