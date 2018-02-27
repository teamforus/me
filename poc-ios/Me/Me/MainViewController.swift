//
//  MainViewController.swift
//  Me
//
//  Created by Jamal on 27/02/2018.
//  Copyright © 2018 Forus. All rights reserved.
//

import UIKit

var mainViewController: MainViewController?

class MainViewController: UIViewController {
    
    @IBOutlet weak var identityView: IdentityView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        mainViewController = self
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
}
