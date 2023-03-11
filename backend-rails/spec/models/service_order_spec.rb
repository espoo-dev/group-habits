# == Schema Information
#
# Table name: service_orders
#
#  id              :bigint           not null, primary key
#  extra_info      :string
#  status          :string           not null
#  creation_date   :datetime
#  conclusion_date :datetime
#  discount        :decimal(8, 2)
#  user_id         :integer          not null
#  customer_id     :integer          not null
#  created_at      :datetime         not null
#  updated_at      :datetime         not null
#
# Indexes
#
#  index_service_orders_on_customer_id  (customer_id)
#  index_service_orders_on_user_id      (user_id)
#
require 'rails_helper'

RSpec.describe ServiceOrder, type: :model do
  context 'relationship' do
    it { should belong_to(:user).required }
    it { should belong_to(:customer).required }
    it { should have_many(:item_service_orders).dependent(:destroy) }
    it { should have_many(:items).through(:item_service_orders).dependent(:destroy) }
  end

  context 'inclusion' do
    it { should validate_inclusion_of(:status).in_array(ServiceOrder::STATUSES) }
  end
end
