//
//  RecordsViewController.swift
//  Me
//
//  Created by Jamal on 27/02/2018.
//  Copyright © 2018 Forus. All rights reserved.
//

import UIKit

class RecordsViewController: UIViewController {
    
    @IBOutlet weak var recordsLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        recordsLabel.text = "Loaded Records VC"
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
