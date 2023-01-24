# == Schema Information
#
# Table name: customers
#
#  id                :bigint           not null, primary key
#  name              :string           not null
#  document_number   :string           not null
#  phone             :string           not null
#  state_inscription :string
#  customer_type     :integer          not null
#  user_id           :string           not null
#  created_at        :datetime         not null
#  updated_at        :datetime         not null
#
# Indexes
#
#  index_customers_on_user_id                      (user_id)
#  index_customers_on_user_id_and_document_number  (user_id,document_number) UNIQUE
#  index_customers_on_user_id_and_name             (user_id,name) UNIQUE
#
FactoryBot.define do
  factory :customer do
    name { 'John' }
    document_number { '00321333355' }
    customer_type { Customer.customer_types[:person] }
    phone { '85996857985' }

    association :user
  end
end
